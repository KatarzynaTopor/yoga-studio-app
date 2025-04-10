import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import Navbar from "./components/Navbar";
import LoginRegister from "./pages/LoginRegister";
import ScheduleList from "./pages/ScheduleList";
import InstructorsPage from "./pages/InstructorsPage";
import MyBookings from "./pages/MyBookings";
import "./App.css";

const App: React.FC = () => {
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(() => {
    return !!sessionStorage.getItem("accessToken");
  });



  useEffect(() => {
    const token = sessionStorage.getItem("accessToken");
    if (token) {
      setIsAuthenticated(true);
    }
  }, []);

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
          <Route path="/my-account" element={<MyBookings />} />
          <Route path="/" element={isAuthenticated ? <Navigate to="/schedule" /> : <Navigate to="/login" />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;