import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Login"
import Signup from "./pages/Signup"
import Shows from "./pages/Shows";
import Create from "./pages/Create";
import SelectTheater from "./pages/SelectTheater";
import SelectScreen from "./pages/SelectScreen";
import AddSeats from "./pages/AddSeats";
import AddShow from "./pages/AddShow";
import UserLogin from "./pages/UserLogin";
import UserSignup from "./pages/UserSignup";
import Home from "./pages/Home";
import Theater from "./pages/Theaters";
import Seats from "./pages/Seats";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<UserLogin />} />
        <Route path="/signup" element={<UserSignup />} />
        <Route path="/home" element={<Home />} />
        <Route path="/theaters" element={<Theater />} />
        <Route path="/seats" element={<Seats />} />
        <Route path="/login" element={<Login />} />
        <Route path="/clientsignup" element={<Signup />} />
        <Route path="/clientshows" element={<Shows />} />
        <Route path="/create" element={<Create />} />
        <Route path="/selecttheater" element={<SelectTheater />} />
        <Route path="/selectscreen" element={<SelectScreen />} />
        <Route path="/addseats" element={<AddSeats />} />
        <Route path="/finalizeshow" element={<AddShow />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
