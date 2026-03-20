import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function UserSignup(){
    const navigate = useNavigate();
    const [userName, setUserName] = useState("");
    const [userEmail, setUserEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [mobileNo, setMobileNo] = useState("");

    const handleSignup = async (e)=>{
        e.preventDefault();
        if(password!==confirmPassword){
            alert("password doesn't match");
            return;
        }

        const respone = await fetch("http://localhost:5431/users/signup",{
            method : "POST",
            headers :{
                "Content-Type" : "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                password: password,
                userName: userName,
                mobileNo: mobileNo,
                userEmail: userEmail
            })
        });

        if(respone.ok){
            alert("signUp successful");
            navigate("/");
        }else{
            alert("signup failed");
        }
    }

    return(
        <div>
            <h2>SignUp Page</h2>
            <form onSubmit={handleSignup}>
                <input
                    type="text"
                    placeholder="Enter unserName"
                    value={userName}
                    onChange={(e)=>setUserName(e.target.value)}
                />
                <br />
                <input
                    type="email"
                    placeholder="Enter Email"
                    value={userEmail}
                    onChange={(e)=>setUserEmail(e.target.value)}
                />
                <br />
                <input
                    type="text"
                    placeholder="Enter Mobile No"
                    value={mobileNo}
                    onChange={e=>setMobileNo(e.target.value)}
                />
                <br />
                <input
                    type="password"
                    placeholder="Enter password"
                    value={password}
                    onChange={e=>setPassword(e.target.value)}
                />
                <br />
                <input
                    type="text"
                    placeholder="confirm password"
                    value={confirmPassword}
                    onChange={e=>setConfirmPassword(e.target.value)}
                />
                <br />
                <button type="submit">SignUp</button>
            </form>
            <br />
            <button onClick={()=>navigate("/login")}>go to Login</button>
        </div>
    )
}