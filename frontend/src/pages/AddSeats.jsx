import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function AddSeats(){
    const [selectedMatrix, setSelectedMatrix] = useState([[]]);
    const navigate = useNavigate();

    useEffect(()=>{
        fetch(
            "http://localhost:5431/screen/getdimensions",{
                credentials: "include"
        })
        .then(data=>data.json())
        .then(data=>{

            const matrix = Array.from(
                {length:data.rows},
                ()=>Array(data.columns).fill(0)
            );

            setSelectedMatrix(matrix);
        });
    }, []);


    const toggleSeat = function(r, c){
        const newMatrix = [...selectedMatrix];

        newMatrix[r][c] = newMatrix[r][c] === 1?0:1;

        setSelectedMatrix(newMatrix);
    }

    const handleSubmit = async function(e) {
        e.preventDefault();
        
        const response = await fetch("http://localhost:5431/seats/addseats",{
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                selectedSeats: selectedMatrix
            })
        })

        if(response.ok){
            alert("done")
            navigate('/finalizeshow');
        }else{
            alert("something went wrong");
        }
    }

    return(
        <div>
            {selectedMatrix.map((row, r)=>(
                <div key={r}>
                    {row.map((seat, c)=>(
                        <input
                            key={`${r}-${c}`}
                            type="checkbox"
                            checked={seat===1}
                            onChange={()=>toggleSeat(r, c)}
                        />
                    ))}
                </div>
            ))}
            <button type="submit" onClick={handleSubmit}>Submit</button>
        </div>
    )
}