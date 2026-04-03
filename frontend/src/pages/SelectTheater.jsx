import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/SelectTheater.css";

export default function SelectTheater() {
    const [theaters, setTheaters] = useState([]);
    const [selectedTheater, setSelectedTheater] = useState("");
    const [newTheater, setNewTheater] = useState("");
    const [longitude, setLongitude] = useState("");
    const [latitude, setLatitude] = useState("");
    const [address, setAddress] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        fetch("http://localhost:5431/theaters/", {
            credentials: "include"
        })
            .then(data => data.json())
            .then(data => {
                setTheaters(data);
            });
    }, []);

    const handleSubmit = async function (e) {
        e.preventDefault();
        
        if (!selectedTheater) {
            alert("Please select a theater first");
            return;
        }

        const response = await fetch("http://localhost:5431/theaters/set", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                theaterId: selectedTheater
            })
        });

        if (response.ok) {
            navigate('/selectscreen');
        } else {
            alert("An error occurred");
        }
    };

    const handleNewTheater = async function (e) {
        e.preventDefault(); // Fixed missing () here

        const response = await fetch("http://localhost:5431/theaters/addnew", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                theaterName: newTheater,
                longitude: parseFloat(longitude),
                latitude: parseFloat(latitude),
                address: address
            })
        });

        if (response.ok) {
            navigate('/selectscreen');
        } else {
            alert("Something went wrong");
        }
    };

    return (
        <div className="theater-wrapper">
            <div className="theater-card">
                
                {/* Section 1: Select Existing */}
                <div className="form-section">
                    <h2 className="section-title">Select Theater</h2>
                    <p className="section-subtitle">Choose an existing theater to continue.</p>
                    
                    <div className="input-group">
                        <select
                            className="theater-input select-input"
                            value={selectedTheater}
                            onChange={(e) => setSelectedTheater(e.target.value)}
                        >
                            <option value={""}>-- Select Theater --</option>
                            {theaters.map(theater => (
                                <option key={theater.theaterId} value={theater.theaterId}>
                                    {`${theater.theaterName}, ${theater.address}`}
                                </option>
                            ))}
                        </select>
                    </div>
                    <button className="btn-primary" type="button" onClick={handleSubmit}>
                        Continue
                    </button>
                </div>

                <div className="divider">or create new</div>

                {/* Section 2: Add New */}
                <div className="form-section">
                    <h2 className="section-title">Add New Theater</h2>
                    <p className="section-subtitle">Register a new location in the system.</p>
                    
                    <form className="theater-form" onSubmit={handleNewTheater}>
                        <div className="input-group">
                            <input
                                className="theater-input"
                                type="text"
                                placeholder="Theater Name"
                                value={newTheater}
                                onChange={(e) => setNewTheater(e.target.value)}
                                required
                            />
                        </div>
                        
                        <div className="input-row">
                            <div className="input-group">
                                <input
                                    className="theater-input"
                                    type="number"
                                    step="any"
                                    placeholder="Longitude"
                                    value={longitude}
                                    onChange={(e) => setLongitude(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="input-group">
                                <input
                                    className="theater-input"
                                    type="number"
                                    step="any"
                                    placeholder="Latitude"
                                    value={latitude}
                                    onChange={(e) => setLatitude(e.target.value)}
                                    required
                                />
                            </div>
                        </div>

                        <div className="input-group">
                            <input
                                className="theater-input"
                                type="text"
                                placeholder="Full Address"
                                value={address}
                                onChange={(e) => setAddress(e.target.value)}
                                required
                            />
                        </div>

                        <button className="btn-secondary" type="submit">
                            Register Theater
                        </button>
                    </form>
                </div>

            </div>
        </div>
    );
}