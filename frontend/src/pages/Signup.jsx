import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Signup.css";

function Signup() {
    const navigate = useNavigate();
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [mobile, setMobile] = useState("");

    const handleSignup = async (e) => {
        e.preventDefault();
        if(password !== confirmPassword){
            alert("password doesn't match");
            return;
        }

        const response = await fetch("http://localhost:5431/clients/signup", {
            method : "POST",
            headers : {
                "Content-Type" : "application/json"
            },
            body: JSON.stringify({
                name: name,
                password: password,
                email: email,
                mobile: mobile
            })
        });

        if(response.ok){
            alert("Signup Successful");
            navigate("/login");
        }else{
            alert("something went wrong");
        }
    };

    return (
        <div className="signup-wrapper">
            <div className="signup-card">
                <h2 className="signup-title">Create an Account</h2>
                
                <form className="signup-form" onSubmit={handleSignup}>
                    <div className="input-group">
                        <input
                            className="signup-input"
                            type="text"
                            placeholder="Enter your name"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            required
                        />
                    </div>

                    <div className="input-group">
                        <input
                            className="signup-input"
                            type="email"
                            placeholder="Enter email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>

                    <div className="input-group">
                        <input
                            className="signup-input"
                            type="text"
                            placeholder="Enter mobile number"
                            value={mobile}
                            onChange={(e) => setMobile(e.target.value)}
                            required
                        />
                    </div>

                    <div className="input-group">
                        <input
                            className="signup-input"
                            type="password"
                            placeholder="Enter Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>

                    <div className="input-group">
                        <input
                            className="signup-input"
                            type="password"
                            placeholder="Confirm Password"
                            value={confirmPassword}
                            onChange={(e) => setConfirmPassword(e.target.value)}
                            required
                        />
                    </div>

                    <button className="btn-primary" type="submit">Sign Up</button>
                    
                    <div className="divider">or</div>
                    
                    <button 
                        className="btn-secondary" 
                        type="button" 
                        onClick={() => navigate("/login")}
                    >
                        Already have an account? Login
                    </button>
                </form>
            </div>
        </div>
    );
}

export default Signup;