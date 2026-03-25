import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Theater.css"; 

const generateDates = () => {
    const dates = [];
    const today = new Date();
    for (let i = 0; i < 7; i++) {
        const nextDate = new Date(today);
        nextDate.setDate(today.getDate() + i);
        dates.push({
            day: nextDate.toLocaleDateString('en-US', { weekday: 'short' }).toUpperCase(),
            date: nextDate.getDate(),
            month: nextDate.toLocaleDateString('en-US', { month: 'short' }).toUpperCase(),
            fullDate: nextDate.toISOString().split('T')[0] 
        });
    }
    return dates;
};

export default function Theater() {
    const navigate = useNavigate();
    const [theaters, setTheaters] = useState([]);
    
    const availableDates = generateDates();
    const [selectedDate, setSelectedDate] = useState(availableDates[0].fullDate);

    useEffect(() => {
        fetch(`http://localhost:5431/usershows/gettheaters?date=${selectedDate}`, {
            credentials: "include"
        })
        .then(data => data.json())
        .then(data => setTheaters(data))
        .catch(err => console.error("Error fetching theaters:", err));
    }, [selectedDate]);

    const handleButton = async (showId) => {
        const response = await fetch("http://localhost:5431/usershows/settheater", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                showId: showId
            })
        });

        if (response.ok) {
            navigate("/seats");
        } else {
            console.error(response);
            alert("Something went wrong");
        }
    };

    return (
        <div className="theater-container">
            <div className="date-strip-container">
                <div className="date-strip">
                    {availableDates.map((dateObj) => (
                        <div 
                            key={dateObj.fullDate}
                            className={`date-card ${selectedDate === dateObj.fullDate ? 'active' : ''}`}
                            onClick={() => setSelectedDate(dateObj.fullDate)}
                        >
                            <span className="date-day">{dateObj.day}</span>
                            <span className="date-number">{dateObj.date}</span>
                            <span className="date-month">{dateObj.month}</span>
                        </div>
                    ))}
                </div>
            </div>

            <div className="theater-list-container">
                <div className="theater-list">
                    {theaters.length > 0 ? (
                        theaters.map(theater => (
                            <div key={theater.theaterName} className="theater-row">
                                <div className="theater-info">
                                    <div className="theater-icon">🎬</div>
                                    <h3 className="theater-name">{theater.theaterName}</h3>
                                </div>

                                <div className="showtime-list">
                                    {theater.showTime.map((time, idx) => (
                                        <div className="showtime-wrapper" key={time}>
                                            <button 
                                                className="showtime-btn"
                                                onClick={() => handleButton(theater.showId[idx])}
                                            >
                                                {time}
                                            </button>
                                            <span className="cancellation-policy">Non-cancellable</span>
                                        </div>
                                    ))}
                                </div>
                            </div>
                        ))
                    ) : (
                        <p className="no-theaters">No theaters available for this date.</p>
                    )}
                </div>
            </div>
        </div>
    );
}