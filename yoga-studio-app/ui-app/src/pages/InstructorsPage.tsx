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

  useEffect(() => {
    fetch("http://localhost:8000/api/instructors")
      .then((res) => res.json())
      .then((data) => setInstructors(data))
      .catch((err) => console.error("Fetch error:", err));
  }, []);

  return (
    <div className="instructors-container">
      <h1 className="page-title">Our Instructors</h1>
      <div className="instructors-list">
        {instructors.map((instructor) => (
          <div key={instructor.id} className="instructor-card">
            <div className="instructor-info">
              <h2>{instructor.name}</h2>
              <p><strong>Specialities:</strong> {instructor.specialties}</p>
              <p><strong>Experience:</strong> {instructor.experience}</p>
              <p><strong>About:</strong> {instructor.bio}</p>
            </div>
            <img
              src={instructor.imageUrl || "https://via.placeholder.com/220"}
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
