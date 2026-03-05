import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";


function Shows(){
    const [shows, setShows] = useState([]);
    const navigate = useNavigate();

    useEffect(()=>{
        fetch("http://localhost:5431/clients/shows",{
            credentials: "include"
        })
        .then(data=>data.json())
        .then(data=>setShows(data))
    }, []);

    console.log(shows);

    return (
        <div>
            <h1>Shows</h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>Show Name</th>
                        <th>Theater ID</th>
                        <th>Screen Layout ID</th>
                        <th>Date & Time</th>
                        <th>Length</th>
                    </tr>
                </thead>

                <tbody>
                    {shows.map((show)=>(
                        <tr key={show.showId}>
                            <td>{show.showName}</td>
                            <td>{show.theaterId}</td>
                            <td>{show.screenLayoutId}</td>
                            <td>{show.showDateAndTime}</td>
                            <td>{show.showLength}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <button type = "submit" onClick={()=>navigate("/create")}>Create a new show</button>
        </div>
    );
}

export default Shows;