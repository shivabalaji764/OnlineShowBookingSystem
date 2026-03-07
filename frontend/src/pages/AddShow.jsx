import { useState } from "react";

export default function AddShow(){
    const [showTime, setShowTime] = useState("");
    const [showLength, setShowLength] = useState("");

    const handleSubmit = async function(e){
        e.preventDefault();

        const response = await fetch("http://localhost:5431/shows/finalize",{
            method: "POST",
            headers: {
                "Content-Type":"application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                showTime: showTime,
                showLength:showLength
            })
        })

        if(response.ok){
            alert("done");
        }else{
            alert("something went wrong");
        }

    }


    return(
        <div>
            <h3>Enter show time and duration</h3>
            <br />
            <p>Enter show time: </p>
            <input
                type="datetime-local"
                placeholder="yyyy-MM-ddTHH:mm"
                value={showTime}
                onChange={(e)=>setShowTime(e.target.value)}
            />
            <br />
            <p>Enter show duration</p>
            <input
                type="time"
                placeholder="HH:mm"
                value={showLength}
                onChange={(e)=>setShowLength(e.target.value)}
            />
            <button type="submit" onClick={handleSubmit}>Submit</button>
        </div>
    )
}