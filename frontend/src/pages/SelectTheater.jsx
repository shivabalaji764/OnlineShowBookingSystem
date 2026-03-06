import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function SelectTheater(){
    const [theaters, setTheaters] = useState([]);
    const [selectedTheater, setSelectedTheater] = useState(0);
    const [newTheater, setNewTheater] = useState("");
    const [longitude, setLongitude] = useState(0.0);
    const [latitude, setLatitude] = useState(0.0);
    const [address, setAddress] = useState("");
    const navigate = useNavigate();

    useEffect(()=>{
        fetch(
            "http://localhost:5431/theaters/",{
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
                theaterId: selectedTheater
            })
        })

        if(response.ok){
            alert("done");
            navigate('/selectscreen');
        }else{
            alert("an error occured");
        }
    }
    
    const handleNewTheater = async function(e){
        e.preventDefault
        
        const response = await fetch("http://localhost:5431/theaters/addnew",{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                theaterName: newTheater,
                longitude: longitude,
                latitude: latitude,
                address: address
            })
        });
        
        if(response.ok){
            alert("done");
            navigate('/selectscreen');
        }else{
            alert("something went wrong");
        }
    }



    return(
        <div>
            <h2>Select Theaters</h2>
            <select
                value={selectedTheater}
                onChange={(e)=>setSelectedTheater(e.target.value)}
            >
                <option value={""}>Select Theater</option>
                {theaters.map(theater=>(
                    <option key={theater.theaterId} value={theater.theaterId}>{`${theater.theaterName}, ${theater.address}`}</option>
                ))}
            </select>
            <button type = "submit" onClick={handleSubmit}>Submit</button>
            <br />
            <h2>Enter details to add a new theater</h2>
            <form onSubmit={handleNewTheater}>
                <input
                    type="text"
                    placeholder="Enter new Theater"
                    value={newTheater}
                    onChange={(e)=>setNewTheater(e.target.value)}
                ></input>
                <br />
                <input
                    type="text"
                    placeholder="Enter longitude"
                    value={longitude}
                    onChange={(e)=>setLongitude(parseFloat(e.target.value))}
                ></input>
                <br />
                <input
                    type="text"
                    placeholder="Enter latitude"
                    value={latitude}
                    onChange={(e)=>setLatitude(parseFloat(e.target.value))}
                ></input>
                <br />
                <input
                    type="text"
                    placeholder="Enter address"
                    value={address}
                    onChange={(e)=>setAddress(e.target.value)}
                ></input>
                <br />
                <button type="submit" >Add new theater</button>
            </form>
        </div>
    )
}