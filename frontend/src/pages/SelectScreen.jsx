import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/SelectScreen.css";

export default function SelectScreen() {
    const [screens, setScreens] = useState([]);
    const [selectedScreen, setSelectedScreen] = useState("");
    const [screenNo, setScreenNo] = useState("");
    const [screenType, setScreenType] = useState("");
    const [basePrice, setBasePrice] = useState("");
    const [rowsCount, setRowsCount] = useState("");
    const [columnsCount, setColumnsCount] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        fetch("http://localhost:5431/screen/get", {
            credentials: "include"
        })
            .then(data => data.json())
            .then(data => {
                setScreens(data);
            });
    }, []);

    const handleSubmit = async function (e) {
        e.preventDefault();
        
        if (!selectedScreen) {
            alert("Please select a screen first.");
            return;
        }

        const response = await fetch("http://localhost:5431/screen/set", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                screenLayoutId: selectedScreen
            })
        });

        if (response.ok) {
            navigate("/finalizeshow");
        } else {
            alert("Something went wrong");
        }
    };

    const handleAddScreen = async function (e) {
        e.preventDefault();

        // Basic validation
        if (!screenNo || !screenType || !basePrice || !rowsCount || !columnsCount) {
            alert("Please fill out all fields to create a screen.");
            return;
        }

        const response = await fetch("http://localhost:5431/screen/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                screenType: screenType,
                screenNo: parseInt(screenNo),
                basePrice: parseFloat(basePrice),
                rows: parseInt(rowsCount),
                columns: parseInt(columnsCount)
            })
        });

        if (response.ok) {
            navigate("/addseats");
        } else {
            alert("Something went wrong");
        }
    };

    return (
        <div className="screen-wrapper">
            <div className="screen-card">
                
                {/* Section 1: Select Existing Screen */}
                <div className="form-section">
                    <h2 className="section-title">Select Screen Layout</h2>
                    <p className="section-subtitle">Choose an existing layout for this show.</p>
                    
                    <form className="screen-form" onSubmit={handleSubmit}>
                        <div className="input-group">
                            <select
                                className="screen-input select-input"
                                value={selectedScreen}
                                onChange={(e) => setSelectedScreen(e.target.value)}
                            >
                                <option value={""}>-- Select Screen --</option>
                                {screens.map(screen => (
                                    <option key={screen.screenLayoutId} value={screen.screenLayoutId}>
                                        Screen {screen.screenNo}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <button className="btn-primary" type="submit">
                            Continue
                        </button>
                    </form>
                </div>

                <div className="divider">or create new layout</div>

                {/* Section 2: Create New Screen Layout */}
                <div className="form-section">
                    <h2 className="section-title">Create New Screen</h2>
                    <p className="section-subtitle">Define dimensions and pricing for a new layout.</p>
                    
                    <form className="screen-form" onSubmit={handleAddScreen}>
                        <div className="input-row">
                            <div className="input-group">
                                <input
                                    className="screen-input"
                                    type="number"
                                    placeholder="Screen No"
                                    value={screenNo}
                                    onChange={(e) => setScreenNo(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="input-group">
                                <input
                                    className="screen-input"
                                    type="text"
                                    placeholder="Screen Type (e.g. IMAX)"
                                    value={screenType}
                                    onChange={(e) => setScreenType(e.target.value)}
                                    required
                                />
                            </div>
                        </div>

                        <div className="input-group">
                            <input
                                className="screen-input"
                                type="number"
                                step="any"
                                placeholder="Base Price (₹)"
                                value={basePrice}
                                onChange={(e) => setBasePrice(e.target.value)}
                                required
                            />
                        </div>

                        <div className="input-row">
                            <div className="input-group">
                                <input
                                    className="screen-input"
                                    type="number"
                                    placeholder="Number of Rows"
                                    value={rowsCount}
                                    onChange={(e) => setRowsCount(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="input-group">
                                <input
                                    className="screen-input"
                                    type="number"
                                    placeholder="Number of Columns"
                                    value={columnsCount}
                                    onChange={(e) => setColumnsCount(e.target.value)}
                                    required
                                />
                            </div>
                        </div>

                        <button className="btn-secondary" type="submit">
                            Create & Add Seats
                        </button>
                    </form>
                </div>

            </div>
        </div>
    );
}