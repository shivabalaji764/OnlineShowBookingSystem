import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Shows.css";

function Shows() {
    const [shows, setShows] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        fetch("http://localhost:5431/clients/shows", {
            credentials: "include"
        })
            .then((data) => data.json())
            .then((data) => {
                const sortedShows = data.sort((a, b) => {
                    return new Date(b.showDateAndTime) - new Date(a.showDateAndTime);
                });
                setShows(sortedShows);
                setIsLoading(false);
            })
            .catch((err) => {
                console.error("Failed to fetch shows", err);
                setIsLoading(false);
            });
    }, []);

    // Optional: Helper to make the date string look better in the UI
    const formatDate = (dateString) => {
        const date = new Date(dateString);
        if (isNaN(date)) return dateString; // Fallback if format is unrecognized
        
        return date.toLocaleDateString(undefined, {
            year: 'numeric',
            month: 'short',
            day: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
        });
    };

    return (
        <div className="shows-page">
            <div className="shows-header-bar">
                <h1 className="shows-title">Manage Shows</h1>
                <button className="btn-create" onClick={() => navigate("/create")}>
                    + Create a new show
                </button>
            </div>

            <div className="shows-card">
                {isLoading ? (
                    <div className="status-message">Loading shows...</div>
                ) : shows.length === 0 ? (
                    <div className="status-message">No shows found.</div>
                ) : (
                    <div className="table-container">
                        <table className="shows-table">
                            <thead>
                                <tr>
                                    <th>Show Name</th>
                                    <th>Theater ID</th>
                                    <th>Screen Layout ID</th>
                                    <th>Date & Time</th>
                                    <th>Length</th>
                                    <th>Total Bookings</th>
                                    <th>Total Earnings</th>
                                </tr>
                            </thead>
                            <tbody>
                                {shows.map((show) => {
                                    const totalSeats = show.bookings.reduce(
                                        (sum, booking) => sum + Number(booking.seats.length || 0), 
                                        0
                                    );
                                    const totalEarning = show.bookings.reduce(
                                        (sum, booking) => sum + Number(booking.cost || 0), 
                                        0
                                    );
                                    

                                    return (
                                        <tr key={show.showId}>
                                            <td className="font-medium">{show.showName}</td>
                                            <td>{show.theaterId}</td>
                                            <td>{show.screenLayoutId}</td>
                                            <td>{formatDate(show.showDateAndTime)}</td>
                                            <td>{show.showLength} mins</td>
                                            <td>{totalSeats}</td>
                                            <td>₹{totalEarning}</td> 
                                        </tr>
                                    );
                                })}
                            </tbody>
                        </table>
                    </div>
                )}
            </div>
        </div>
    );
}

export default Shows;