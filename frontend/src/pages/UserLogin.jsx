import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function UserLogin(){
    const navigate = useNavigate();
    const [userEmail, setUserEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async function(e){
        e.preventDefault();

        const respone = await fetch("http://localhost:5431/users/login",{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                userEmail: userEmail,
                password: password
            })

        });

        if(respone.ok){
            alert("Login Successful");
            navigate("/home");
        }else{
            alert("Login Failed");
        }
    }

    return(
        <div>
            <h2>Login Page</h2><br />
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Enter name"
                    value={userEmail}
                    onChange={(e)=>setUserEmail(e.target.value)}
                />
                <br />
                <input
                    type="password"
                    placeholder="Enter password"
                    value={password}
                    onChange={(e)=>setPassword(e.target.value)}
                />
                <br />
                <button type="submit">Login</button>
            </form><br />
            <h3>new to the site click here to Signup</h3><br />
            <button  type = "submit" onClick={()=>navigate("/signup")}>SignUp</button>
        </div>
    );
}