import React from "react";
import { Link } from "react-router-dom";
import "./Footer.css";

const Footer: React.FC = () => {
  return (
    <footer className="footer">
      <div className="footer-left">
        <div className="footer-visit">
          <p className="visit-title">VISIT US</p>
          <p className="visit-text">
            Swallow’s Nest Yoga Studio<br />
            Swan Lake, Poland 30-300
          </p>
        </div>

        <div className="footer-contact">
          <p className="contact-title">CONTACT US</p>
          <p className="contact-text">
            Email: info@swallowsnestyoga.com<br />
            Phone: (123) 456-7890
          </p>
          <p className="hours-title">STUDIO HOURS</p>
          <p className="hours-text">
            Mon - Fri: 7:00 AM – 8:00 PM<br />
            Sat - Sun: 8:00 AM – 6:00 PM
          </p>
        </div>
      </div>

      <div className="footer-right">
        <div className="footer-links">
          <p className="footer-heading">Products</p>
          <Link to="/about">About</Link>
          <Link to="/events">Events</Link>
          <Link to="/press">Press</Link>
          <Link to="/contact">Contact</Link>
        </div>

        <div className="footer-social">
          <p className="footer-heading">Social Links</p>
          <Link to="/contact">Email</Link>
          <a href="https://twitter.com" target="_blank" rel="noopener noreferrer">Twitter</a>
          <a href="https://medium.com" target="_blank" rel="noopener noreferrer">Medium</a>
          <a href="https://instagram.com" target="_blank" rel="noopener noreferrer">Instagram</a>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
