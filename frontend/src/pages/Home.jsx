import { useEffect, useState } from "react";
import { Navigate } from "react-router-dom";
import { useNavigate } from "react-router-dom";

export default function Home(){
    const navigate = useNavigate();
    const [shows, setShows] = useState([]);

    useEffect(()=>{
        fetch("http://localhost:5431/usershows/shows",{
            credentials: "include"
        })
        .then(data=>data.json())
        .then(data=>setShows(data))
    }, []);
    console.log(shows);

    const handleButton = async function(show){
        const respone = await fetch("http://localhost:5431/usershows/setshow",{
            method: "POST",
            headers: {
                "Content-Type" : "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                showName:show
            })
        });

        if(respone.ok){
            alert("Show Selected");
            navigate("/theaters");
        }else{
            alert("something went wrong");
        }
    }

    return(
        <div>
            <h2>Available shows</h2>
            {shows.map(show=>(
                <button key={show} onClick={()=>{
                    handleButton(show);
                }}>{show}</button>
            ))}
        </div>
    )
}