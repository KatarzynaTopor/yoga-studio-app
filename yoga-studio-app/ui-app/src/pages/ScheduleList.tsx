// src/pages/ScheduleList.tsx
import React, { useEffect, useState } from "react";

interface ScheduleItem {
  id: string;
  title: string;
  description: string;
  location: string;
  durationInMinutes: number;
  scheduleTime: string;
  instructorName: string;
}

const ScheduleList: React.FC = () => {
  const [schedules, setSchedules] = useState<ScheduleItem[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`${import.meta.env.VITE_BACKEND_URL}/schedule`)
      .then((res) => res.json())
      .then((data) => {
        setSchedules(data);
        setLoading(false);
      })
      .catch(() => setLoading(false));
  }, []);

  const formatTime = (isoString: string) => {
    const date = new Date(isoString);
    return date.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" });
  };

  return (
    <div className="schedule-container">
      <h2 className="schedule-heading">Schedule</h2>
      {loading ? (
        <p>Loading...</p>
      ) : (
        schedules.map((item) => (
          <div key={item.id} className="schedule-card">
            <div className="time-block">
              <strong>{formatTime(item.scheduleTime)}</strong>
              <p>{Math.floor(item.durationInMinutes / 60)} H {item.durationInMinutes % 60} MIN</p>
            </div>
            <div className="location-block">{item.location}</div>
            <div className="class-block">
              <strong>{item.title}</strong>
              <p>{item.instructorName}</p>
            </div>
            <button className="sign-up-btn">SIGN UP</button>
          </div>
        ))
      )}
    </div>
  );
};

export default ScheduleList;
