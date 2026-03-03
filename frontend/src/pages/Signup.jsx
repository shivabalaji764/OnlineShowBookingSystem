import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Signup () {

    const navigate = useNavigate();
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [mobile, setMobile] = useState("");

    const handleSignup = async (e) => {
        e.preventDefault();
        if(password!==confirmPassword){
            alert("password doesn't match");
            return;
        }

        const response = await fetch("http://localhost:5431/clients/signup", {
            method : "POST",
            headers : {
                "Content-Type" : "application/json"
            },
            body: JSON.stringify({
                name:name,
                password: password,
                email: email,
                mobile: mobile
            })
        });

        if(response.ok){
            alert("Signup Successful");
            navigate("/")
        }else{
            alert("something went wrong");
        }
    };

    return(
        <div>
            <h2>SignUp form</h2>
            <form onSubmit={handleSignup}>
                <input
                    type = "text"
                    placeholder="Enter your name"
                    value={name}
                    onChange={(e)=>setName(e.target.value)}
                />
                <br />

                <input
                    type = "email"
                    placeholder="Enter email"
                    value={email}
                    onChange={(e)=>setEmail(e.target.value)}
                />
                <br />

                <input
                    type = "password"
                    placeholder="Enter Password"
                    value={password}
                    onChange={(e)=>setPassword(e.target.value)}
                />
                <br />

                <input
                    type = "password"
                    placeholder="Confirm Password"
                    value={confirmPassword}
                    onChange={(e)=>setConfirmPassword(e.target.value)}
                />
                <br />

                <input
                    type = "text"
                    placeholder="Enter mobile number"
                    value={mobile}
                    onChange={(e)=>setMobile(e.target.value)}
                />

                <button type = "submit">Sign Up</button>
                <br />
                <button type = "button" onClick={()=>navigate("/")}>Login</button>
            </form>
        </div>
    );
}

export default Signup;