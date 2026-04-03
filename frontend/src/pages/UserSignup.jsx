import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/UserSignup.css";

export default function UserSignup() {
    const navigate = useNavigate();
    const [userName, setUserName] = useState("");
    const [userEmail, setUserEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [mobileNo, setMobileNo] = useState("");

    const handleSignup = async (e) => {
        e.preventDefault();
        if (password !== confirmPassword) {
            alert("password doesn't match");
            return;
        }

        const response = await fetch("http://localhost:5431/users/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                password: password,
                userName: userName,
                mobileNo: mobileNo,
                userEmail: userEmail
            })
        });

        if (response.ok) {
            alert("signUp successful");
            navigate("/");
        } else {
            alert("signup failed");
        }
    };

    return (
        <div className="login-page">
            <div className="login-left">
                <div className="login-left-orb login-left-orb-1"></div>
                <div className="login-left-orb login-left-orb-2"></div>
                <div className="login-left-orb login-left-orb-3"></div>
                
                <div className="login-bubbles">
                    <div className="bubble"></div>
                    <div className="bubble"></div>
                    <div className="bubble"></div>
                    <div className="bubble"></div>
                    <div className="bubble"></div>
                </div>

                <div className="login-left-content">
                    <div className="login-logo">
                        <div className="login-logo-icon">🎬</div>
                        <div className="login-logo-name">ShowTime</div>
                    </div>
                    
                    <h1 className="login-hero-title">
                        Start your<br /><span>Journey</span>
                    </h1>
                    
                    <p className="login-hero-sub">
                        Create an account today to escape from endless queues for show tickets.
                    </p>
                    
                    <div className="login-features">
                        <div className="login-feature">
                            <div className="login-feature-icon">✓</div>
                            <div className="login-feature-text">Secure & Reliable</div>
                        </div>
                        <div className="login-feature">
                            <div className="login-feature-icon">✓</div>
                            <div className="login-feature-text">Seamless Experience</div>
                        </div>
                        <div className="login-feature">
                            <div className="login-feature-icon">✓</div>
                            <div className="login-feature-text">24/7 Support</div>
                        </div>
                    </div>
                </div>
            </div>

            <div className="login-right">
                <div className="login-card">
                    <div className="login-card-header">
                        <div className="login-card-eyebrow">Get Started</div>
                        <h2 className="login-card-title">User Sign Up</h2>
                        <p className="login-card-sub">Fill in your details to create an account.</p>
                    </div>

                    <form className="login-form" onSubmit={handleSignup}>
                        <div className="login-field">
                            <label className="login-label">User Name</label>
                            <input
                                className="login-input"
                                type="text"
                                placeholder="Enter userName"
                                value={userName}
                                onChange={(e) => setUserName(e.target.value)}
                                required
                            />
                        </div>

                        <div className="login-field">
                            <label className="login-label">Email</label>
                            <input
                                className="login-input"
                                type="email"
                                placeholder="Enter Email"
                                value={userEmail}
                                onChange={(e) => setUserEmail(e.target.value)}
                                required
                            />
                        </div>

                        <div className="login-field">
                            <label className="login-label">Mobile Number</label>
                            <input
                                className="login-input"
                                type="text"
                                placeholder="Enter Mobile No"
                                value={mobileNo}
                                onChange={(e) => setMobileNo(e.target.value)}
                                required
                            />
                        </div>

                        <div className="login-field">
                            <label className="login-label">Password</label>
                            <input
                                className="login-input"
                                type="password"
                                placeholder="Enter password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                        </div>

                        <div className="login-field">
                            <label className="login-label">Confirm Password</label>
                            <input
                                className="login-input"
                                type="password"
                                placeholder="Confirm password"
                                value={confirmPassword}
                                onChange={(e) => setConfirmPassword(e.target.value)}
                                required
                            />
                        </div>

                        <button className="login-btn-primary" type="submit">Sign Up</button>

                        <div className="login-divider">
                            <div className="login-divider-line"></div>
                            <span className="login-divider-text">OR</span>
                            <div className="login-divider-line"></div>
                        </div>

                        <div className="login-signup-cta">
                            <p>Already have an account?</p>
                            <button
                                className="login-btn-secondary"
                                type="button"
                                onClick={() => navigate("/")}
                            >
                                Login
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}