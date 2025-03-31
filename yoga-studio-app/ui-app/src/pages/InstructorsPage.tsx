import React, { useEffect, useState } from "react";
import InstructorCard from "../components/InstructorCard";

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
    fetch("/api/instructors")
      .then((res) => res.json())
      .then((data) => setInstructors(data));
  }, []);

  return (
    <div className="instructors-container">
      <h1 className="page-title">Our Instructors</h1>
      {instructors.map((instructor) => (
        <InstructorCard key={instructor.id} instructor={instructor} />
      ))}
    </div>
  );
};

export default InstructorsPage;
