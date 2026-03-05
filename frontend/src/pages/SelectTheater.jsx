import { useState, useEffect } from "react";

export default function SelectTheater(){
    const [theaters, setTheaters] = useState([]);
    const [selectedTheater, setSelectedTheater] = useState("");

    useEffect(()=>{
        fetch(
            "https://localhost:5431/theaters/",{
            credentials: "include"
        })
        .then(data=>data.json())
        .then(data=>{
            setTheaters(data)
            console.log(data);
        })
    }, [])
    const handleSubmit = async function(e){
        e.preventDefault();

        const response = await fetch("http://localhost:5431/theaters/set",{
            method: "POST",
            headers: {
                "Content-Type":"application/json"
            },
            credentials:"include",
            body: JSON.stringify({
                theaterName: selectedTheater
            })
        })

        if(response.ok){
            alert("done");
        }else{
            alert("an error occured");
        }
    }



    return(
        <div>
            <h2>Select Theaters</h2>
            <select
                value={selectedTheater}
                onChange={(e)=>setSelectedTheater(e.target.value)}
            >
                {theaters.map(theater=>(
                    <option key={theater.theaterId} value={theater.theaterName}>{`${theater.theaterName} ${theater.address}`}</option>
                ))}
            </select>
            <button type = "submit" onClick={handleSubmit}>Submit</button>
        </div>
    )
}