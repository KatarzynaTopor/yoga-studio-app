import React, { useState } from "react";
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import Navbar from "./components/Navbar";
import LoginRegister from "./pages/LoginRegister";
import ScheduleList from "./pages/ScheduleList";
import InstructorsPage from "./pages/InstructorsPage";
import "./App.css";

const App: React.FC = () => {
    const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);

    return (
        <Router>
            <div className="navbar-wrapper">
                <Navbar />
            </div>
            <div className="app-content">
                <Routes>
                    <Route path="/login" element={<LoginRegister setIsAuthenticated={setIsAuthenticated} />} />
                    <Route path="/register" element={<LoginRegister setIsAuthenticated={setIsAuthenticated} />} />
                    <Route path="/schedule" element={<ScheduleList />} />
                    <Route path="/instructors" element={<InstructorsPage />} />
                    <Route path="/" element={isAuthenticated ? <h1>Home Page (Protected)</h1> : <Navigate to="/login" />} />
                </Routes>
            </div>
        </Router>
    );
};

export default App;
