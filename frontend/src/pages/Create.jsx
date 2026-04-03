import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Create.css";

function Create() {
    const [shows, setShows] = useState([]);
    const [selectedShow, setSelectedShow] = useState("");
    const [newShow, setNewShow] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        fetch("http://localhost:5431/shows/", {
            credentials: "include"
        })
            .then(data => data.json())
            .then(data => setShows(data))
            .catch(err => console.error("Error fetching shows:", err));
    }, []);

    // Helper function to handle the API call
    const submitShowData = async (showName) => {
        const response = await fetch("http://localhost:5431/shows/setshow", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                showName: showName
            })
        });

        if (response.ok) {
            navigate("/selecttheater");
        } else {
            alert(`Something Went wrong: ${response.status}`);
        }
    };

    const handleSelectSubmit = async function (e) {
        e.preventDefault();
        if (!selectedShow) {
            alert("Please select a show from the list.");
            return;
        }
        submitShowData(selectedShow);
    };

    const handleCreateSubmit = async function (e) {
        e.preventDefault();
        if (!newShow.trim()) {
            alert("Please enter a name for the new show.");
            return;
        }
        submitShowData(newShow);
    };

    return (
        <div className="create-wrapper">
            <div className="create-card">
                
                {/* Section 1: Select Existing Show */}
                <div className="form-section">
                    <h2 className="section-title">Select Show</h2>
                    <p className="section-subtitle">Choose an existing show from the database.</p>
                    
                    <form className="create-form" onSubmit={handleSelectSubmit}>
                        <div className="input-group">
                            <select
                                className="create-input select-input"
                                value={selectedShow}
                                onChange={(e) => setSelectedShow(e.target.value)}
                            >
                                <option value="">-- Select Show --</option>
                                {shows.map(show => (
                                    <option key={show.showName} value={show.showName}>
                                        {show.showName}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <button className="btn-primary" type="submit">
                            Continue with Selected
                        </button>
                    </form>
                </div>

                <div className="divider">or create new</div>

                {/* Section 2: Create New Show */}
                <div className="form-section">
                    <h2 className="section-title">Create New Show</h2>
                    <p className="section-subtitle">Enter a name to register a brand new show.</p>
                    
                    <form className="create-form" onSubmit={handleCreateSubmit}>
                        <div className="input-group">
                            <input
                                className="create-input"
                                type="text"
                                placeholder="Enter show name here"
                                value={newShow}
                                onChange={(e) => setNewShow(e.target.value)}
                            />
                        </div>
                        <button className="btn-secondary" type="submit">
                            Create & Continue
                        </button>
                    </form>
                </div>

            </div>
        </div>
    );
}

export default Create;