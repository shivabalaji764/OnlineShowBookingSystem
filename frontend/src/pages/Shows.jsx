import { useState, useEffect } from "react";

function Shows(){
    const [shows, setShows] = useState([]);

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
                        <tr key={show.show_id}>
                            <td>{show.show_name}</td>
                            <td>{show.theater_id}</td>
                            <td>{show.screenLayout_id}</td>
                            <td>{show.show_date_and_time}</td>
                            <td>{show.show_length}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default Shows;