import { useState, useEffect } from "react";
import "../styles/Seats.css";

export default function Seats() {
    const [seats, setSeats] = useState([]);
    const [screen, setScreen] = useState({});
    const [show, setShow] = useState({});
    const [selectedSeats, setSelectedSeats] = useState([]);

    useEffect(() => {
        fetch("http://localhost:5431/showseats/get", {
            credentials: "include"
        })
        .then(data => data.json())
        .then(data => {
            setShow(data.showModel || {});
            setScreen(data.screenLayout || {});
            setSeats(data.showSeats || []);
        })
        .catch(err => console.error("Error fetching seats:", err));
    }, []);

    const toggleSeat = function(seatId) {
        setSelectedSeats(prev => {
            if (prev.includes(seatId)) {
                return prev.filter(seat => seat !== seatId);
            } 
            else {
                if (prev.length >= 6) {
                    alert("You can only select up to 6 seats");
                    return prev;
                }
                return [...prev, seatId];
            }
        });
    }

    const handleSubmit = async function() {
        if (selectedSeats.length === 0) {
            alert("Please select at least one seat.");
            return;
        }

        const response = await fetch("http://localhost:5431/showseats/set", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                selectedSeats: selectedSeats,
                cost: screen.basePrice * selectedSeats.length
            })
        });

        if (response.ok) {
            alert("Saved successfully!");
        } else if (response.status === 423) {
            alert("One or more seats are no longer available. Please refresh the page.");
        } else {
            alert("Something went wrong: " + response.status);
        }
    }

    const renderSeat = (seat, columnIndex) => {
        if (seat.status === 2) {
            return <div key={seat.showSeatId || `walkway-${columnIndex}`} className="seat walkway"></div>;
        }

        const isSelected = selectedSeats.includes(seat.showSeatId);
        const seatLabel = seat.seatNumber || (columnIndex + 1); 

        if (seat.status === -1) {
            return (
                <div key={seat.showSeatId} className="seat booked">
                    X
                </div>
            );
        }

        if (seat.status === 0) {
            return (
                <div key={seat.showSeatId} className="seat locked">
                    {seatLabel}
                </div>
            );
        }

        return (
            <div 
                key={seat.showSeatId} 
                className={`seat available ${isSelected ? 'selected' : ''}`}
                onClick={() => toggleSeat(seat.showSeatId)}
            >
                {seatLabel}
            </div>
        );
    };

    return (
        <div className="seats-page-container">
            <header className="seats-header">
                <h2>Select Your Seats</h2>
                {show.showDateAndTime && <p className="show-time">{new Date(show.showDateAndTime).toLocaleString()}</p>}
                {screen.screenLayoutId && <p className="screen-info">Screen {screen.screenLayoutId}</p>}
            </header>

            <div className="seat-legend">
                <div className="legend-item"><div className="seat available"></div> Available</div>
                <div className="legend-item"><div className="seat selected"></div> Selected</div>
                <div className="legend-item"><div className="seat booked">X</div> Sold</div>
                <div className="legend-item"><div className="seat locked"></div> Locked</div>
            </div>

            <div className="seating-layout">
                {seats.map((row, rowIndex) => (
                    <div className="seat-row" key={rowIndex}>
                        <span className="row-label">{String.fromCharCode(65 + rowIndex)}</span>
                        {row.map((seat, colIndex) => renderSeat(seat, colIndex))}
                    </div>
                ))}
            </div>

            <div className="movie-screen-container">
                <div className="movie-screen"></div>
                <p>All eyes this way</p>
            </div>

            <div className="seats-footer">
                <div className="seats-footer-content">
                    <div className="price-info">
                        {selectedSeats.length > 0 ? (
                            <p>{selectedSeats.length} Ticket(s) • Total: <strong>₹{screen.basePrice * selectedSeats.length}</strong></p>
                        ) : (
                            <p>No seats selected</p>
                        )}
                    </div>
                    <button 
                        className="submit-btn" 
                        onClick={handleSubmit}
                        disabled={selectedSeats.length === 0}
                    >
                        Book Tickets
                    </button>
                </div>
            </div>
        </div>
    );
}