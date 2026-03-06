import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Login"
import Signup from "./pages/Signup"
import Shows from "./pages/Shows";
import Create from "./pages/Create";
import SelectTheater from "./pages/SelectTheater";
import SelectScreen from "./pages/SelectScreen";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/shows" element={<Shows />} />
        <Route path="/create" element={<Create />} />
        <Route path="/selecttheater" element={<SelectTheater />} />
        <Route path="/selectscreen" element={<SelectScreen />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
