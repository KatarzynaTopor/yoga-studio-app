import React from "react";
import "./InstructorCard.css";

type Instructor = {
  id: string;
  name: string;
  specialties: string;
  experience: string;
  bio: string;
  imageUrl: string;
};

const InstructorCard: React.FC<{ instructor: Instructor }> = ({ instructor }) => {
  return (
    <div className="instructor-card">
      <div className="instructor-info">
        <h2>{instructor.name}</h2>
        <p><strong>Specialities:</strong> {instructor.specialties}</p>
        <p><strong>Experience:</strong> {instructor.experience}</p>
        <p><strong>About:</strong> {instructor.bio}</p>
      </div>
      <img
        src={instructor.imageUrl}
        alt={instructor.name}
        className="instructor-image"
      />
    </div>
  );
};

export default InstructorCard;
