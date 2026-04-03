import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Home.css";

export default function Home() {
    const navigate = useNavigate();
    const [shows, setShows] = useState([]);
    const [searchQuery, setSearchQuery] = useState("");

    useEffect(() => {
        fetch("http://localhost:5431/usershows/shows", {
            credentials: "include"
        })
        .then(data => data.json())
        .then(data => setShows(data))
        .catch(error => console.error("Error fetching shows:", error));
    }, []);

    const handleButton = async function(show) {
        const response = await fetch("http://localhost:5431/usershows/setshow", {
            method: "POST",
            headers: {
                "Content-Type" : "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                showName: show
            })
        });

        if (response.ok) {
            navigate("/theaters");
        } else {
            alert("Something went wrong");
        }
    }

    const filteredShows = shows.filter(show => 
        show.toLowerCase().includes(searchQuery.toLowerCase())
    );

    return (
        <div className="home-container">
            <header className="home-header">
                <div className="header-title-row">
                    <h2>Available Shows</h2>
                    <button 
                        className="view-bookings-btn" 
                        onClick={() => navigate("/mybookings")}
                    >
                        View My Bookings
                    </button>
                </div>
                <input 
                    type="text" 
                    className="search-bar" 
                    placeholder="Search for a show..." 
                    value={searchQuery}
                    onChange={(e) => setSearchQuery(e.target.value)}
                />
            </header>

            <div className="shows-grid">
                {filteredShows.length > 0 ? (
                    filteredShows.map(show => (
                        <button 
                            key={show} 
                            className="show-card"
                            onClick={() => handleButton(show)}
                        >
                            <div className="card-image-placeholder"></div>
                            <h3 className="card-title">{show}</h3>
                        </button>
                    ))
                ) : (
                    <p className="no-results">No shows found matching "{searchQuery}"</p>
                )}
            </div>
        </div>
    );
}