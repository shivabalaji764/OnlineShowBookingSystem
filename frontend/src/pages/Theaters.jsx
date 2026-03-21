import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function Theater(){
    const navigate = useNavigate();
    const [theaters, setTheaters] = useState([]);

    useEffect(()=>{
        fetch("http://localhost:5431/usershows/gettheaters",{
            credentials: "include"
        })
        .then(data=>data.json())
        .then(data=>setTheaters(data))
    },[]);

    const handleButton = async (showId)=>{
        const response = await fetch("http://localhost:5431/usershows/settheater",{
            method:"POST",
            headers:{
                "Content-Type": "application/json"
            },
            credentials:"include",
            body: JSON.stringify({
                showId:showId
            })
        });

        if(response.ok){
            alert("theater selected");
            navigate("/seats");
        }else{
            console.log(response);
            alert("something went wrong");
        }
    }


    return(
        <div>
            <h2>Select theater</h2>
            <div>
                {theaters.map(theater=>(
                    <div key={theater.theaterName}>
                        <h3>{theater.theaterName}</h3>
                        <br />
                        {theater.showTime.map((showTime, idx)=>(
                            <button key={showTime} onClick={()=>handleButton(theater.showId[idx])}>{showTime}</button>
                        ))}
                    </div>
                ))}
                
            </div>
        </div>
    )
}