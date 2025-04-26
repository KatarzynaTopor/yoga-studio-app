import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./AddSchedule.css";

const CLASS_OPTIONS = [
  "Hatha Yoga",
  "Vinyasa Yoga",
  "Ashtanga Yoga",
  "Yin Yoga",
];

const ROOM_OPTIONS = ["206", "207", "208", "501"];
const LOCATION_OPTIONS = ["Krakow Studio", "Warsaw Studio"];
const LEVEL_OPTIONS = ["Beginner", "Beginner +", "Intermediate", "Advanced"];

const AddSchedule: React.FC = () => {
  const navigate = useNavigate();

  const [title, setTitle] = useState(CLASS_OPTIONS[0]);
  const [description, setDescription] = useState("");
  const [scheduleTime, setScheduleTime] = useState("");
  const [capacity, setCapacity] = useState(20);
  const [location, setLocation] = useState(LOCATION_OPTIONS[0]);
  const [room, setRoom] = useState(ROOM_OPTIONS[0]);
  const [duration, setDuration] = useState(60);
  const [level, setLevel] = useState(LEVEL_OPTIONS[0]);
  const [instructorId, setInstructorId] = useState<string>("");

  useEffect(() => {
    const role = sessionStorage.getItem("role");
    const storedInstructorId = sessionStorage.getItem("instructorId");

    if (role !== "TEACHER") {
      alert("Only instructors can add schedules.");
      navigate("/homepage");
      return;
    }

    if (!storedInstructorId) {
      alert("Instructor profile missing. Please contact administrator.");
      navigate("/teacher-panel");
      return;
    }

    setInstructorId(storedInstructorId);
  }, [navigate]);

  const token = sessionStorage.getItem("accessToken");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!instructorId || !token) {
      alert("Missing authentication. Please log in again.");
      navigate("/login");
      return;
    }

    const body = {
      title,
      description,
      instructorId,
      scheduleTime,
      capacity,
      location,
      room,
      duration,
      level,
    };

    try {
      const response = await fetch("http://localhost:8000/api/schedule", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(body),
      });

      if (response.ok) {
        alert("✅ Schedule added successfully!");
        navigate("/teacher-panel");
      } else {
        const data = await response.json();
        console.error("❌ Backend error:", data);
        alert(data.message || "Failed to create schedule.");
      }
    } catch (err) {
      console.error("🔥 Network error:", err);
      alert("An error occurred while submitting.");
    }
  };

  return (
    <div className="add-schedule-container">
      <form onSubmit={handleSubmit} className="add-schedule-form">
        <h2 className="page-title">Add New Schedule</h2>

        <select value={title} onChange={(e) => setTitle(e.target.value)} className="input" required>
          {CLASS_OPTIONS.map((cls) => (
            <option key={cls} value={cls}>
              {cls}
            </option>
          ))}
        </select>

        <textarea
          placeholder="Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
          className="textarea"
        />

        <input
          type="datetime-local"
          value={scheduleTime}
          onChange={(e) => setScheduleTime(e.target.value)}
          required
          className="input"
        />

        <select value={location} onChange={(e) => setLocation(e.target.value)} className="input" required>
          {LOCATION_OPTIONS.map((loc) => (
            <option key={loc} value={loc}>
              {loc}
            </option>
          ))}
        </select>

        <select value={room} onChange={(e) => setRoom(e.target.value)} className="input" required>
          {ROOM_OPTIONS.map((r) => (
            <option key={r} value={r}>
              {r}
            </option>
          ))}
        </select>

        <select value={level} onChange={(e) => setLevel(e.target.value)} className="input" required>
          {LEVEL_OPTIONS.map((lvl) => (
            <option key={lvl} value={lvl}>
              {lvl}
            </option>
          ))}
        </select>

        <input
          type="number"
          placeholder="Capacity"
          value={capacity}
          onChange={(e) => setCapacity(Number(e.target.value))}
          min={1}
          required
          className="input"
        />

        <input
          type="number"
          placeholder="Duration (minutes)"
          value={duration}
          onChange={(e) => setDuration(Number(e.target.value))}
          min={1}
          required
          className="input"
        />

        <button type="submit" className="submit-button">
          Add Schedule
        </button>
      </form>
    </div>
  );
};

export default AddSchedule;
