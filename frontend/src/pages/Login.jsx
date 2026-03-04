import { useState } from "react";
import { useNavigate } from "react-router-dom";

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
        } else {
            alert("Invalid credentials");
        }
    };

    return (
        <div>
            <h2>Client Login</h2>

            <form onSubmit={handleLogin}>
                <div>
                    <input
                        type="email"
                        placeholder="Enter Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>

                <br />

                <div>
                    <input
                        type="password"
                        placeholder="Enter Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>

                <br />

                <button type="submit">Login</button>
                <br />

                <button type = "button" onClick={()=>navigate("/signup")}>Go to Signup</button>
            </form>
        </div>
    );
}

export default Login;