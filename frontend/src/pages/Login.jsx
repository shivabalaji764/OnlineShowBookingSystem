import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Login.css";

function Login() {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = async (e) => {
        e.preventDefault();

        const response = await fetch("http://localhost:5431/clients/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                email: email,
                password: password
            })
        });

        if (response.ok) {
            alert("Login successful!");
            navigate('/clientshows');
        } else {
            alert("Invalid credentials");
        }
    };

    return (
        <div className="login-wrapper">
            <div className="login-card">
                <h2 className="login-title">Client Login</h2>

                <form className="login-form" onSubmit={handleLogin}>
                    <div className="input-group">
                        <input
                            className="login-input"
                            type="email"
                            placeholder="Enter Email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>

                    <div className="input-group">
                        <input
                            className="login-input"
                            type="password"
                            placeholder="Enter Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>

                    <button className="btn-primary" type="submit">
                        Login
                    </button>
                    
                    <div className="divider">or</div>

                    <button 
                        className="btn-secondary" 
                        type="button" 
                        onClick={() => navigate("/clientsignup")}
                    >
                        Create an account
                    </button>
                </form>
            </div>
        </div>
    );
}

export default Login;