import { useState, useEffect } from "react";

export default function Seats(){
    const [seats, setSeats] = useState([]);
    const [screen, setScreen] = useState({});
    const [show, setShow] = useState({});
    const [selectedSeats, setSelectedSeats]  = useState([]);

    useEffect(()=>{
        fetch("http://localhost:5431/showseats/get",{
            credentials: "include"
        })
        .then(data=>data.json())
        .then(data=>{
            setShow(data.showModel);
            setScreen(data.screenLayout);
            setSeats(data.showSeats);
            console.log(data);
        })
    }, []);


    const toggleSeat = function(seatId){
        if(selectedSeats.length==6){
            alert("you can only select upto 6 seats");
            return;
        }

        setSelectedSeats(prev=>{
            if(prev.includes(seatId)){
                return prev.filter(seat=> seat !== seatId);
            }else{
                return [...prev, seatId];
            }
        })
    }

    const handleSubmit = function(){
        
        const response = fetch("http://localhost:5431/showseats/set",{
            method: "POST",
            headers:{
                "Content-Type": "application/json"
            },
            credentials:"include",
            body: JSON.stringify({
                selectedSeats: selectedSeats,
                cost: screen.basePrice
            })
        });


        if(response.ok){
            alert("saved successfully");
        }else if(response.status===423){
            alert("one or many seats are not available, refresh the page to see available seats");
        }else{
            alert("something went wrong "+response.status);
        }
    }

    return(
        <div>
            <h2>Select Seats</h2>
            <p>{show.showDateAndTime}</p>
            <p>{screen.screenLayoutId}</p>
            {seats.map((row, r)=>(
                <div key={r}>
                    {row.map(seat=>{

                        if(seat.status===2) return <div key={seat.showSeatId} style={{ width: "20px", height: "20px" }}></div>
                        
                        return(
                            <input
                                key={seat.showSeatId}
                                type="checkbox"
                                disabled = {seat.status!==1}
                                checked = {seat.status===-1 || selectedSeats.includes(seat.showSeatId)}
                                onChange={()=>toggleSeat(seat.showSeatId)}
                            />
                        )
                    })}
                    <br />
                </div>
            ))}
            <br />
            <button type="submit" onClick={()=>handleSubmit()}>Submit</button>
        </div>
    )
}