import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/AddSeats.css";

export default function AddSeats() {
    const [selectedMatrix, setSelectedMatrix] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        fetch("http://localhost:5431/screen/getdimensions", {
            credentials: "include"
        })
            .then(data => data.json())
            .then(data => {
                const matrix = Array.from(
                    { length: data.rows },
                    () => Array(data.columns).fill(0)
                );
                setSelectedMatrix(matrix);
                setIsLoading(false);
            })
            .catch(err => {
                setIsLoading(false);
            });
    }, []);

    const toggleSeat = function (r, c) {
        const newMatrix = selectedMatrix.map(row => [...row]);
        newMatrix[r][c] = newMatrix[r][c] === 1 ? 0 : 1;
        setSelectedMatrix(newMatrix);
    };

    const handleSubmit = async function (e) {
        e.preventDefault();

        const response = await fetch("http://localhost:5431/seats/addseats", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                selectedSeats: selectedMatrix
            })
        });

        if (response.ok) {
            navigate('/finalizeshow');
        }
    };

    return (
        <div className="seats-wrapper">
            <div className="seats-card">
                <div className="seats-header">
                    <h2 className="section-title">Configure Seat Layout</h2>
                    <p className="section-subtitle">Click to mark seats as available (active) or unavailable (empty).</p>
                </div>

                {isLoading ? (
                    <div className="loading-state">Loading layout...</div>
                ) : (
                    <div className="layout-container">
                        
                        <div className="seat-matrix">
                            {selectedMatrix.map((row, r) => (
                                <div className="seat-row" key={r}>
                                    <span className="row-label">{String.fromCharCode(65 + r)}</span>
                                    
                                    {row.map((seat, c) => (
                                        <button
                                            key={`${r}-${c}`}
                                            type="button"
                                            className={`seat ${seat === 1 ? 'selected' : 'available'}`}
                                            onClick={() => toggleSeat(r, c)}
                                        >
                                            {c + 1}
                                        </button>
                                    ))}
                                    
                                    <span className="row-label right">{String.fromCharCode(65 + r)}</span>
                                </div>
                            ))}
                        </div>

                        <div className="screen-indicator">
                            <span>SCREEN</span>
                            <div className="screen-curve"></div>
                        </div>

                        <div className="seat-legend">
                            <div className="legend-item">
                                <div className="seat available"></div>
                                <span>Empty Space</span>
                            </div>
                            <div className="legend-item">
                                <div className="seat selected"></div>
                                <span>Active Seat</span>
                            </div>
                        </div>

                        <button className="btn-primary" type="button" onClick={handleSubmit}>
                            Save Layout
                        </button>
                    </div>
                )}
            </div>
        </div>
    );
}