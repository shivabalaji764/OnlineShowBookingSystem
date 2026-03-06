import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function SelectScreen(){
    const [screens, setScreens] = useState([]);
    const [selectedScreen, setSelectedScreen] = useState(0);
    const [screenNo, setScreenNo] = useState(0);
    const [screenType, setScreenType] = useState("");
    const [basePrice, setBasePrice] = useState(0.0);
    const [rowsCount, setRowsCount] = useState(0);
    const [columnsCount, setColumnsCount] = useState(0);
    const navigate = useNavigate();

    useEffect(()=>{
        fetch(
            "http://localhost:5431/screen/get",{
            credentials: "include"
        })
        .then(data=>data.json())
        .then(data=>{
            console.log(data);
            setScreens(data);
        });
    }, [])

    const handleSubmit = async function(e){
        e.preventDefault();
        
        const response = await fetch("http://localhost:5431/screen/set",{
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                screenLayoutId: selectedScreen
            })
        })

        if(response.ok){
            alert("done")
            navigate("/finalizeshow");
        }else{
            alert("something went wrong")
        }
    }

    const handleAddScreen = async function(e){
        e.preventDefault();

        const response = await fetch("http://localhost:5431/screen/add",{
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                screenType: screenType,
                screenNo: screenNo,
                basePrice: basePrice,
                rows: rowsCount,
                columns: columnsCount
            })
        })

        if(response.ok){
            alert("done");
            navigate("/addseats")
        }else{
            alert("Something went wrong");
        }
    }

    return(
        <div>
            <h2>Select the screen</h2>
            <select
                value={selectedScreen}
                onChange={(e)=>setSelectedScreen(e.target.value)}
            >
                <option value={""}>Select Screen</option>
                {screens.map(screen=>(
                    <option key={screen.screenLayoutId} value={screen.screenLayoutId}>{screen.screenNo}</option>
                ))}
            </select>
            <br />
            <button type="submit" onClick={handleSubmit}>Submit</button>
            <br />
            <br />
            <h2>Create a new screen here</h2>
            <form onSubmit={handleAddScreen}>
                <input
                    type="text"
                    placeholder="Enter Screen No"
                    value={screenNo}
                    onChange={(e)=>setScreenNo(parseInt(e.target.value))}
                ></input>
                <input 
                    type="text"
                    placeholder="Enter Screen Type"
                    value={screenType}
                    onChange={(e)=>setScreenType(e.target.value)}
                ></input>
                <input
                    type = "text"
                    placeholder="Enter base price"
                    value={basePrice}
                    onChange={(e)=>setBasePrice(parseFloat(e.target.value))}
                ></input>
                <input
                    type="text"
                    placeholder="Enter Number of rows"
                    value={rowsCount}
                    onChange={(e)=>setRowsCount(parseInt(e.target.value))}
                ></input>
                <input
                    type="text"
                    placeholder="Enter Number of columns"
                    value={columnsCount}
                    onChange={(e)=>setColumnsCount(parseInt(e.target.value))}
                ></input>
                <button type="submit">Submit</button>
            </form>
        </div>
    )
} 