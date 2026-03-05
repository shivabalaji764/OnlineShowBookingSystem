import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function Create(){
    const [shows, setShows] = useState([]);
    const [selectedShow, setSelectedShow] = useState("");
    const navigate = useNavigate();

    useEffect(()=>{
        fetch(
            "http://localhost:5431/shows/",{
            credentials: "include"
        })
        .then(data=>data.json())
        .then(data=>setShows(data))
    }, []);

    const handleSubmit = async function(e){
        e.preventDefault();
        const response = await fetch("http://localhost:5431/shows/setshow",{
            method: "POST",
            headers: {
                "Content-Type":"application/json"
            },
            credentials:"include",
            body: JSON.stringify({
                show_name:selectedShow
            })
        });

        if(response.ok){
            alert("done");
            navigate("/selecttheater");
        }else{
            alert(`Something Went wrong: ${response.status}`);
        }
    }

    return(
        <div>
            <select
                value = {selectedShow}
                onChange={(e)=>setSelectedShow(e.target.value)}
            >
            <option value="">Select Show</option>
            {shows.map(show=>(
                <option key = {show.showName} value={show.showName}>{show.showName}</option>
            ))}
            </select>
            <button type = "submit" onClick={handleSubmit}>Submit</button>
            <br />
            <h2>Enter the name here to create a new show</h2>
            <br />
            <form method="POST">
                <input
                    type = "text"
                    placeholder="Enter show name here"
                    value={selectedShow}
                    onChange={(e)=>setSelectedShow(e.target.value)}
                ></input>
                <button type = "submit" onClick={handleSubmit}>Create a new show</button>
            </form>
        </div>
    )
}

export default Create;