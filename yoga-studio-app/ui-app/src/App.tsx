import React, { useState } from "react";
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import Navbar from "./components/Navbar";
import LoginRegister from "./pages/LoginRegister";
import ScheduleList from "./pages/ScheduleList";


const App: React.FC = () => {
    const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);

    return (
        <Router>
            <Navbar />
            <Routes>
                <Route path="/login" element={<LoginRegister setIsAuthenticated={setIsAuthenticated} />} />
                <Route path="/register" element={<LoginRegister setIsAuthenticated={setIsAuthenticated} />} />
                <Route path="/schedule" element={<ScheduleList />} />
                {/*  Protect Home Route */}
                <Route path="/" element={isAuthenticated ? <h1>Home Page (Protected)</h1> : <Navigate to="/login" />} />
            </Routes>
        </Router>
    );
};

export default App;
