import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "./ScheduleList.css";

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
  const navigate = useNavigate();
  const { id: userId } = useParams<{ id: string }>();

  const fetchBookings = async () => {
    const token = localStorage.getItem("accessToken");
    if (!token || !userId) {
      navigate("/login");
      return;
    }
    console.log("Wysłano zapytanie GET o rezerwacje użytkownika:", userId);
    try {
      const response = await fetch(`http://localhost:8000/api/users/${userId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });


      if (response.ok) {
        const data = await response.json();
        setBookedClasses(data);
      } else {
        console.error("Failed to fetch bookings");
      }
    } catch (error) {
      console.error("Error fetching bookings", error);
    }
  };

  const handleSignOut = async (scheduleId: string) => {
    const token = localStorage.getItem("accessToken");
    try {
      const response = await fetch(`http://localhost:8000/api/schedule/${scheduleId}/cancel`, {
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
    if (userId) {
      fetchBookings();
    }
  }, [userId]);

  return (
    <div className="schedule-container">
      <h2 className="page-title">Classes booked by user {userId}</h2>
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
              <button className="sign-up-button" onClick={() => handleSignOut(c.id)}>SIGN OUT</button>
            </div>
          );
        })}
      </div>
      <div style={{ marginTop: "2rem", textAlign: "center" }}>
            <button className="refresh-button" onClick={fetchBookings}>
                Odśwież listę zajęć
            </button>
          </div>
    </div>
  );
};

export default MyBookings;
