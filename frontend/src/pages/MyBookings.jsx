import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/MyBookings.css";

export default function MyBookings() {
    const [bookings, setBookings] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [selectedBooking, setSelectedBooking] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        fetch("http://localhost:5431/bookings/get", {
            credentials: "include"
        })
            .then(res => res.json())
            .then(data => {
                const sortedData = data.sort((a, b) => {
                    return new Date(b.showDateAndTime) - new Date(a.showDateAndTime);
                });
                setBookings(sortedData);
                setIsLoading(false);
            })
            .catch(err => {
                console.log(err);
                setIsLoading(false);
            });
    }, []);

    const isPastShow = (booking) => {
        if (!booking.showDateAndTime) return false;
        const showTimeMs = new Date(booking.showDateAndTime).getTime();
        const durationMs = (booking.showLength || 0) * 60 * 1000;
        const currentTimeMs = new Date().getTime();
        return (showTimeMs + durationMs) < currentTimeMs;
    };

    const extractDate = (dateString) => {
        if (!dateString) return "";
        const date = new Date(dateString);
        return date.toISOString().split('T')[0];
    };

    const extractTime = (dateString) => {
        if (!dateString) return "";
        const date = new Date(dateString);
        return date.toTimeString().split(' ')[0];
    };

    return (
        <div className="bookings-wrapper">
            <div className="bookings-header-bar">
                <h1 className="bookings-title">My Bookings</h1>
                <button className="btn-back" onClick={() => navigate("/home")}>
                    Back to Home
                </button>
            </div>

            <div className="bookings-container">
                {isLoading ? (
                    <div className="status-msg">Loading your bookings...</div>
                ) : bookings.length === 0 ? (
                    <div className="status-msg">You have no bookings yet.</div>
                ) : (
                    <div className="bookings-grid">
                        {bookings.map((booking, index) => {
                            const past = isPastShow(booking);
                            return (
                                <div 
                                    key={booking.bookingId || index} 
                                    className={`booking-card-mini ${past ? 'past' : 'active'}`}
                                    onClick={() => setSelectedBooking(booking)}
                                >
                                    <div className="card-mini-status">
                                        {past ? "Completed" : "Upcoming"}
                                    </div>
                                    <h3 className="card-mini-title">
                                        {booking.showName || "Unknown Show"}
                                    </h3>
                                    <p className="card-mini-date">
                                        {extractDate(booking.showDateAndTime)} • {extractTime(booking.showDateAndTime)}
                                    </p>
                                </div>
                            );
                        })}
                    </div>
                )}
            </div>

            {selectedBooking && (
                <div className="modal-overlay" onClick={() => setSelectedBooking(null)}>
                    <div className="modal-content" onClick={(e) => e.stopPropagation()}>
                        <button className="modal-close" onClick={() => setSelectedBooking(null)}>✕</button>
                        
                        <div className="ticket-details">
                            <p><strong>Show Name:</strong> {selectedBooking.showName}</p>
                            <p><strong>Theater:</strong> {selectedBooking.theaterName}</p>
                            <p><strong>Screen:</strong> {selectedBooking.screenNo}</p>
                            <p><strong>Address:</strong> {selectedBooking.address}</p>
                            
                            <hr className="ticket-divider" />
                            
                            <p><strong>Date:</strong> {extractDate(selectedBooking.showDateAndTime)}</p>
                            <p><strong>Time:</strong> {extractTime(selectedBooking.showDateAndTime)}</p>
                            
                            <hr className="ticket-divider" />
                            
                            <p><strong>Seats:</strong> {selectedBooking.seats}</p>
                            
                            <p className="ticket-price">
                                <strong>Total Price:</strong> ₹{selectedBooking.totalPrice}
                            </p>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}