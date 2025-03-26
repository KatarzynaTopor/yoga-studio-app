
import React, { useEffect, useState } from "react";
import "./ScheduleList.css";

interface Schedule {
  id: string;
  title: string;
  description: string;
  scheduleTime: string;
  capacity: number;
  instructorName: string;
  duration: number;
}

const ScheduleList: React.FC = () => {
  const [schedules, setSchedules] = useState<Schedule[]>([]);
  const [error, setError] = useState("");

  const fetchSchedules = async () => {
    try {
      const response = await fetch("http://localhost:8000/api/schedule");
      const data = await response.json();
      setSchedules(data);
    } catch (err) {
      console.error(err);
      setError("Failed to load schedule.");
    }
  };

  useEffect(() => {
    fetchSchedules();
  }, []);

  return (
    <div className="schedule-container">
      <div className="schedule-list">
        {schedules.map((s) => {
          const time = new Date(s.scheduleTime);
          const endTime = new Date(time.getTime() + s.duration * 60000);
          return (
            <div key={s.id} className="schedule-card">
              <div className="schedule-time">
                <strong>{time.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })}</strong>
                <div className="schedule-duration">{Math.floor(s.duration / 60)} H {s.duration % 60} MIN</div>
              </div>
              <div className="schedule-details">
                <div className="schedule-location">Krakow Studio,<br/>class 201</div>
                <div className="schedule-info">
                  <div className="schedule-title">{s.title}</div>
                  <div className="schedule-instructor">{s.instructorName}</div>
                </div>
              </div>
              <button className="sign-up-button">SIGN UP</button>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default ScheduleList;
