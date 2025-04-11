import React, { useState } from "react";

const AddSchedule: React.FC = () => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [scheduleTime, setScheduleTime] = useState("");
  const [capacity, setCapacity] = useState(20);
  const [location, setLocation] = useState("");
  const [room, setRoom] = useState("");
  const [duration, setDuration] = useState(60);
  const [instructorId, setInstructorId] = useState("");

  const token = sessionStorage.getItem("accessToken");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const body = {
      title,
      description,
      scheduleTime,
      capacity,
      location,
      room,
      duration,
      instructorId,
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
        alert("Schedule added successfully.");
      } else {
        const data = await response.json();
        alert(data.message || "Failed to create schedule.");
      }
    } catch (err) {
      console.error("Error creating schedule:", err);
      alert("An error occurred.");
    }
  };

  return (
    <div>
      <h2>Add New Schedule</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <input
            type="text"
            placeholder="Title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
        </div>
        <div>
          <input
            type="text"
            placeholder="Description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
        </div>
        <div>
          <input
            type="datetime-local"
            value={scheduleTime}
            onChange={(e) => setScheduleTime(e.target.value)}
            required
          />
        </div>
        <div>
          <input
            type="number"
            placeholder="Capacity"
            value={capacity}
            onChange={(e) => setCapacity(Number(e.target.value))}
            required
          />
        </div>
        <div>
          <input
            type="text"
            placeholder="Location"
            value={location}
            onChange={(e) => setLocation(e.target.value)}
            required
          />
        </div>
        <div>
          <input
            type="text"
            placeholder="Room"
            value={room}
            onChange={(e) => setRoom(e.target.value)}
            required
          />
        </div>
        <div>
          <input
            type="number"
            placeholder="Duration (minutes)"
            value={duration}
            onChange={(e) => setDuration(Number(e.target.value))}
            required
          />
        </div>
        <div>
          <input
            type="text"
            placeholder="Instructor ID"
            value={instructorId}
            onChange={(e) => setInstructorId(e.target.value)}
            required
          />
        </div>
        <button type="submit">Add Schedule</button>
      </form>
    </div>
  );
};

export default AddSchedule;
