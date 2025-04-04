import React from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Navbar.css";

const Navbar: React.FC = () => {
    const navigate = useNavigate();
    const isAuthenticated = !!localStorage.getItem("accessToken");

    const handleBookClick = () => {
        if (isAuthenticated) {
            navigate("/schedule");
        } else {
            navigate("/login");
        }
    };

    return (
        <nav className="navbar">
            <div className="nav-section nav-left">
                <Link to="/homepage" className="nav-link">Home</Link>
                <Link to="/classes" className="nav-link">Classes</Link>
                <Link to="/instructors" className="nav-link">Instructors</Link>
            </div>

            <div className="nav-section nav-center">
                <Link to="/homepage" className="studio-name">Swallow’s Nest Yoga</Link>
            </div>

            <div className="nav-section nav-right">
                <Link to="/schedule" className="nav-link">Schedule</Link>
                <button className="book-class-button" onClick={handleBookClick}>
                    Book a Class
                </button>
            </div>
        </nav>
    );
};

export default Navbar;
