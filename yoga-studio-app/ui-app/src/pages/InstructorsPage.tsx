import React, { useEffect, useState } from "react";
import "./InstructorsPage.css";

type Instructor = {
  id: string;
  name: string;
  specialties: string;
  experience: string;
  bio: string;
  imageUrl: string;
};

const InstructorsPage: React.FC = () => {
  const [instructors, setInstructors] = useState<Instructor[]>([]);
  const [error, setError] = useState("");

  useEffect(() => {
    fetch("http://localhost:8000/api/instructors")
      .then((res) => {
        if (!res.ok) {
          throw new Error("Failed to fetch instructors");
        }
        return res.json();
      })
      .then((data) => setInstructors(data))
      .catch((err) => {
        console.error("Fetch error:", err);
        setError("Could not load instructors.");
      });
  }, []);

  const resolveImageUrl = (url: string): string => {
    if (!url) return "https://via.placeholder.com/400x300";
    if (url.startsWith("/uploads/")) {
      return `http://localhost:8000${url}`;
    }
    return url; // fallback: full external URL
  };

  return (
    <div className="instructors-container">
      {error && <p className="error-message">{error}</p>}
      <div className="instructors-list">
        {instructors.map((instructor) => (
          <div key={instructor.id} className="instructor-card">
            <div className="instructor-info">
              <h2>{instructor.name}</h2>
              <p><strong>Specialties:</strong> {instructor.specialties}</p>
              <p><strong>Experience:</strong> {instructor.experience}</p>
              <p><strong>About:</strong> {instructor.bio}</p>
            </div>
            <img
              src={resolveImageUrl(instructor.imageUrl)}
              alt={instructor.name}
              className="instructor-image"
            />
          </div>
        ))}
      </div>
    </div>
  );
};

export default InstructorsPage;
