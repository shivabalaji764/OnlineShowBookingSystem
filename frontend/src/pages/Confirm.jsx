import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import "../styles/Confirm.css";

export default function Confirm() {
    const [details, setDetails] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    const navigate = useNavigate();

    useEffect(() => {
        fetch("http://localhost:5431/bookings/", {
            credentials: "include"
        })
            .then(res => {
                if (!res.ok) {
                    throw new Error("Failed to fetch booking details");
                }
                return res.json();
            })
            .then(data => {
                setDetails(data);
                setIsLoading(false);
            })
            .catch(err => {
                setError(err.message);
                setIsLoading(false);
            });
    }, []);

    const handleConfirm = async () => {
        try {
            const res = await fetch("http://localhost:5431/bookings/confirm", {
                method: "POST",
                credentials: "include"
            });

            if (!res.ok) {
                const errorMsg = await res.text();
                throw new Error(errorMsg || "Booking failed");
            }

            alert("Booking Confirmed! Enjoy the show.");
            navigate("/home");

        } catch (error) {
            alert("There was an issue confirming your booking (probably timeout). " + error.message);
        }
    };

    useEffect(() => {
    const timer = setTimeout(() => {
        alert("Session expired. Redirecting...");
        navigate("/home");
    }, 570000);

    return () => clearTimeout(timer);
}, [navigate]);

    if (isLoading) return <div>Loading your booking details...</div>;
    if (error) return <div>Error loading details: {error}</div>;
    if (!details) return <div>No booking details found.</div>;

    return (
        <div className="card">
            <h2 className="title">Confirm Your Booking</h2>
            
            <div className="infoBox">
                <p><strong>Show Name:</strong> {details.showName}</p>
                <p><strong>Theater:</strong> {details.theater}</p>
                <p><strong>Screen:</strong> {details.screen}</p>
                <p><strong>Address:</strong> {details.address}</p>
                
                <hr className="divider" />
                
                <p><strong>Date:</strong> {details.date.split('T')[0]}</p>
                <p><strong>Time:</strong> {details.date.split('T')[1].split('.')[0]}</p>
                
                <hr className="divider" />
                
                <p><strong>Seats:</strong> {details.seats}</p>
                
                {details.totalPrice && (
                    <p className="totalPrice"><strong>Total Price:</strong> ₹{details.totalPrice}</p>
                )}
            </div>

            <button onClick={handleConfirm} className="button">
                Pay Now
            </button>
        </div>
    );
}