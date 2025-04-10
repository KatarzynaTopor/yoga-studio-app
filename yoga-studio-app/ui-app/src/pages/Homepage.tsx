import React from "react";
import { useNavigate } from "react-router-dom";
import "./Homepage.css";
import heroImage from "../assets/logo.jpg";
import studioPhoto from "../assets/studio.jpg";

const Homepage: React.FC = () => {
  const navigate = useNavigate();
  const token = sessionStorage.getItem("accessToken");

  const handleSignUpClick = () => {
    if (token) {
      navigate("/schedule");
    } else {
      navigate("/login");
    }
  };

  return (
    <div className="homepage-container">
      <section className="hero-section">
        <div className="hero-text">
          <h1>YOGA CLASSES FOR EVERYONE</h1>
          <p>Join us in the best yoga studio in town</p>
          <button className="sign-up-button" onClick={handleSignUpClick}>
            SIGN UP NOW!
          </button>
        </div>

        {/* Logo w prawym dolnym rogu */}
        <div className="hero-logo-wrapper">
          <img className="hero-logo" src={heroImage} alt="Logo" />
        </div>
      </section>

      <section className="class-types">
        {[
          { title: "Hatha Yoga", desc: "A gentle, foundational yoga style focusing on basic postures, breathing, and relaxation. Great for beginners and improving flexibility." },
          { title: "Vinyasa Yoga", desc: "A dynamic, flowing practice linking breath with movement. It’s energetic and often includes sequences like sun salutations." },
          { title: "Ashtanga Yoga", desc: "A structured, intense practice with a set sequence of poses. It builds strength, flexibility, and endurance." },
          { title: "Yin Yoga", desc: "A slow, meditative practice that involves holding poses for longer durations (2-5 minutes) to stretch deep tissues and promote relaxation." }
        ].map(({ title, desc }) => (
          <div className="class-card" key={title}>
            <h3>{title}</h3>
            <p>{desc}</p>
          </div>
        ))}
      </section>

      <section className="about-section">
        <div className="about-text">
          <h2>About us</h2>
          <p>
            At Swallow’s Nest Yoga, we believe in creating a sanctuary for movement, mindfulness, and transformation.
            Our studio is a welcoming space for yogis of every level. Come explore your breath, build strength, move with grace,
            and cultivate peace in your soul. Take flight and embrace your journey.
          </p>
        </div>
        <div className="about-image">
          <img src={studioPhoto} alt="Studio Interior" />
        </div>
      </section>
    </div>
  );
};

export default Homepage;
