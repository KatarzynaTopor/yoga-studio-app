import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./MyBookings.css";

interface Schedule {
  id: string;
  title: string;
  scheduleTime: string;
  duration: number;
  instructorName: string;
  location: string;
  room: string;
}

const MyBookings: React.FC = () => {
  const [bookedClasses, setBookedClasses] = useState<Schedule[]>([]);
  const [userName, setUserName] = useState<string>("");
  const navigate = useNavigate();

  const userId = sessionStorage.getItem("userId");

  const fetchBookings = async () => {
    const token = sessionStorage.getItem("accessToken");
    if (!token || !userId) {
      navigate("/login");
      return;
    }

    try {
      const response = await fetch(`http://localhost:8000/api/users/${userId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (response.ok) {
        const data = await response.json();
        setUserName(data.username || "");
        setBookedClasses(data);
      } else {
        console.error("Failed to fetch bookings");
      }
    } catch (error) {
      console.error("Error fetching bookings", error);
    }
  };

  const handleSignOut = async (scheduleId: string) => {
    const token = sessionStorage.getItem("accessToken");
    try {
      const response = await fetch(`http://localhost:8000/api/schedule/${scheduleId}/cancel?userId=${userId}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (response.ok) {
        setBookedClasses(prev => prev.filter(c => c.id !== scheduleId));
      } else {
        alert("Failed to sign out of class.");
      }
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchBookings();
  }, []);

  return (
    <div className="schedule-container">
      <h2 className="page-title">
        {userName ? `Classes booked by ${userName}` : "Your Booked Classes"}
      </h2>

      <div className="schedule-list">
        {bookedClasses.map((c) => {
          const time = new Date(c.scheduleTime);
          return (
            <div key={c.id} className="schedule-card">
              <div className="schedule-time">
                <strong>{time.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })}</strong>
                <div className="schedule-duration">
                  {Math.floor(c.duration / 60)} H {c.duration % 60} MIN
                </div>
              </div>
              <div className="schedule-details">
                <div className="schedule-location">
                  {c.location},<br />{c.room}
                </div>
                <div className="schedule-info">
                  <div className="schedule-title">{c.title}</div>
                  <div className="schedule-instructor">{c.instructorName}</div>
                </div>
              </div>
              <button className="sign-up-button" onClick={() => handleSignOut(c.id)}>
                SIGN OUT
              </button>
            </div>
          );
        })}
      </div>

      <button className="refresh-button" onClick={fetchBookings}>
        Refresh List of Classes
      </button>

      <button
        className="logout-button"
        onClick={() => {
          sessionStorage.clear();
          navigate("/login");
        }}
      >
        Logout
      </button>
    </div>
  );
};

export default MyBookings;
