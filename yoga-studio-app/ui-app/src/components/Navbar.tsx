import React from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";

const Navbar: React.FC = () => {
    return (
        <nav className="navbar">
            {/* Left Section */}
            <div className="nav-left">
                <Link to="/" className="nav-link">Home</Link>
                <Link to="/classes" className="nav-link">Classes</Link>
                <Link to="/instructors" className="nav-link">Instructors</Link>
            </div>

            {/* Centered Studio Name */}
            <div className="nav-center">
                            <Link to="/" className="studio-name">Swallow’s Nest Yoga</Link> {}
             </div>

            {/* Right Section */}
            <div className="nav-right">
                <Link to="/schedule" className="nav-link schedule-link">Schedule</Link>
                <button className="book-class-button">Book a Class</button>
            </div>
        </nav>
    );
};

export default Navbar;
