import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  format,
  isSameDay,
  addDays,
  startOfToday,
  addWeeks,
} from "date-fns";
import "./ScheduleList.css";

interface Schedule {
  id: string;
  title: string;
  description: string;
  scheduleTime: string;
  capacity: number;
  instructorName: string;
  duration: number;
  location: string;
  room: string;
  booked: number;
}

const ScheduleList: React.FC = () => {
  const [schedules, setSchedules] = useState<Schedule[]>([]);
  const [bookingStatus, setBookingStatus] = useState<{ [key: string]: string }>({});
  const [selectedDate, setSelectedDate] = useState<Date>(startOfToday());
  const [weekOffset, setWeekOffset] = useState(0);
  const navigate = useNavigate();

  const fetchSchedules = async () => {
    try {
      const response = await fetch("http://localhost:8000/api/schedule");
      const data = await response.json();
      setSchedules(data);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchSchedules();
  }, []);

  const handleSignUp = async (scheduleId: string) => {
    const token = sessionStorage.getItem("accessToken");
    if (!token) {
      navigate("/login");
      return;
    }

    try {
      const response = await fetch(`http://localhost:8000/api/schedule/${scheduleId}/book`, {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (response.ok) {
        setBookingStatus((prev) => ({
          ...prev,
          [scheduleId]: " Signed up successfully!",
        }));
        fetchSchedules();
      } else {
        const errorText = await (response?.text?.() ?? "An error occurred.");
        setBookingStatus((prev) => ({
          ...prev,
          [scheduleId]: ` ${errorText}`,
        }));
      }
    } catch (error) {
      console.error(error);
      setBookingStatus((prev) => ({
        ...prev,
        [scheduleId]: " An error occurred.",
      }));
    }
  };

  const scrollToTop = () => {
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const weekStart = addWeeks(startOfToday(), weekOffset);

  return (
    <div className="schedule-container">
      <div className="day-picker-wrapper">
        <button
          className="week-arrow"
          onClick={() => {
            setWeekOffset((w) => w - 1);
            scrollToTop();
          }}
        >
          ←
        </button>
        <div className="day-picker">
          {[...Array(7)].map((_, i) => {
            const date = addDays(weekStart, i);
            const isActive = isSameDay(date, selectedDate);
            return (
              <button
                key={i}
                className={`day-button ${isActive ? "active" : ""}`}
                onClick={() => setSelectedDate(date)}
              >
                <div className="day-name">{format(date, "EEE").toUpperCase()}</div>
                <div className="day-date">{format(date, "dd MMM")}</div>
              </button>
            );
          })}
        </div>
        <button
          className="week-arrow"
          onClick={() => {
            setWeekOffset((w) => w + 1);
            scrollToTop();
          }}
        >
          →
        </button>
      </div>

      <button className="refresh-button" onClick={fetchSchedules}>
        Refresh Schedule
      </button>

      <div className="schedule-list">
        {schedules
          .filter((s) => isSameDay(new Date(s.scheduleTime), selectedDate))
          .sort((a, b) => new Date(a.scheduleTime).getTime() - new Date(b.scheduleTime).getTime())
          .map((s) => {
            const time = new Date(s.scheduleTime);
            const percentBooked = Math.min((s.booked / s.capacity) * 100, 100);

            return (
              <div key={s.id} className="schedule-card">
                <div className="schedule-time">
                  <strong>
                    {time.toLocaleTimeString([], {
                      hour: "2-digit",
                      minute: "2-digit",
                    })}
                  </strong>
                  <div className="schedule-duration">
                    {Math.floor(s.duration / 60)} H {s.duration % 60} MIN
                  </div>
                </div>

                <div className="schedule-details">
                  <div className="schedule-location">
                    {s.location},<br />
                    {s.room}
                  </div>

                  <div className="schedule-info">
                    <div className="schedule-title">{s.title}</div>
                    <div className="schedule-instructor">{s.instructorName}</div>

                    <div className="schedule-description">
                      {s.description}
                    </div>
                  </div>

                  <div className="booking-progress">
                    <div className="capacity-bar">
                      <div
                        className="capacity-fill"
                        style={{ width: `${percentBooked}%` }}
                      ></div>
                    </div>
                    <div className="capacity-text">
                      {s.booked} / {s.capacity} booked
                    </div>
                  </div>
                </div>

                <div className="signup-section">
                  <button
                    className="sign-up-button"
                    onClick={() => handleSignUp(s.id)}
                  >
                    SIGN UP
                  </button>
                  {bookingStatus[s.id] && (
                    <p className="booking-message">{bookingStatus[s.id]}</p>
                  )}
                </div>
              </div>
            );
          })}
      </div>
    </div>
  );
};

export default ScheduleList;
