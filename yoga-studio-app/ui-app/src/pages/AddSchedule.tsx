import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const AddSchedule: React.FC = () => {
  const navigate = useNavigate();

  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [scheduleTime, setScheduleTime] = useState("");
  const [capacity, setCapacity] = useState(20);
  const [location, setLocation] = useState("");
  const [room, setRoom] = useState("");
  const [duration, setDuration] = useState(60);
  const [instructorId, setInstructorId] = useState<string>("");

  useEffect(() => {
    const role = sessionStorage.getItem("role");
    const storedInstructorId = sessionStorage.getItem("instructorId");

    if (role !== "TEACHER") {
      alert("Only instructors can add schedules.");
      navigate("/homepage"); // 🚪 send normal users to homepage
      return;
    }

    if (!storedInstructorId) {
      alert("Instructor profile missing. Please contact administrator.");
      navigate("/teacher-panel"); // 🛠️ maybe let teacher see their panel
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
    };

    console.log("🚀 Submitting schedule:", body);

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
    <div className="p-6 max-w-2xl mx-auto">
      <h2 className="text-3xl font-bold mb-6">Add New Schedule</h2>
      <form onSubmit={handleSubmit} className="flex flex-col gap-4">
        <input
          type="text"
          placeholder="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
          className="input"
        />
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
          type="text"
          placeholder="Location"
          value={location}
          onChange={(e) => setLocation(e.target.value)}
          required
          className="input"
        />
        <input
          type="text"
          placeholder="Room"
          value={room}
          onChange={(e) => setRoom(e.target.value)}
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
        <button type="submit" className="btn btn-primary">
          Add Schedule
        </button>
      </form>
    </div>
  );
};

export default AddSchedule;
