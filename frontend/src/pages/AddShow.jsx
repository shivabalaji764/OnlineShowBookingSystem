import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/AddShow.css";

export default function AddShow() {
    const [showTime, setShowTime] = useState("");
    const [showLength, setShowLength] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async function (e) {
        e.preventDefault();

        const response = await fetch("http://localhost:5431/shows/finalize", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                showTime: showTime,
                showLength: showLength
            })
        });

        if (response.ok) {
            alert("Show successfully finalized!");
            navigate("/clientshows");
        } else {
            alert("Something went wrong");
        }
    };

    return (
        <div className="add-show-wrapper">
            <div className="add-show-card">
                <div className="form-section">
                    <h2 className="section-title">Finalize Show Details</h2>
                    <p className="section-subtitle">Set the scheduled time and duration for this show.</p>
                    
                    <form className="add-show-form" onSubmit={handleSubmit}>
                        
                        <div className="input-group">
                            <label className="input-label">Show Date & Time</label>
                            <input
                                className="add-show-input"
                                type="datetime-local"
                                value={showTime}
                                onChange={(e) => setShowTime(e.target.value)}
                                required
                            />
                        </div>

                        <div className="input-group">
                            <label className="input-label">Duration (Hours : Minutes)</label>
                            <input
                                className="add-show-input"
                                type="time"
                                value={showLength}
                                onChange={(e) => setShowLength(e.target.value)}
                                required
                            />
                        </div>

                        <button className="btn-primary" type="submit">
                            Finalize Show
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
}