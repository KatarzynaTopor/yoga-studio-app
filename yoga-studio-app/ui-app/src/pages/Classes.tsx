import React, { useEffect, useState } from "react";
import "./Classes.css";

interface Course {
  title: string;
  duration: string;
  level: string;
  instructor: string;
  yogaClass: string;
}

const levels = [
  { name: "Beginner", description: "Perfect for those new to yoga. Focus on foundational poses, breathwork, and gentle movement.", stars: 1 },
  { name: "Beginner +", description: "A step up from beginner. Introduces more challenging poses and transitions while keeping a slow and mindful pace.", stars: 2 },
  { name: "Intermediate", description: "Designed for those with knowledge of dynamic flows. More demanding strength, space poses, and breath control.", stars: 4 },
  { name: "Advanced", description: "For experienced yogis. Complex postures, long holds, deeper inversions, and refinement of strength, flexibility, and mindfulness.", stars: 5 },
];

const classTypes = [
  { name: "Hatha Yoga", description: "A gentle foundational yoga style focusing on breath, alignment, relaxation and flexibility." },
  { name: "Vinyasa Yoga", description: "A dynamic, flowing practice linking breath with movement. Energetic and includes sequences like sun salutations." },
  { name: "Ashtanga Yoga", description: "A structured, intense practice with a sequence of poses. It builds strength, flexibility, and endurance." },
  { name: "Yin Yoga", description: "A slow meditative practice that involves holding postures (2-5 minutes) to stretch deep tissues and promote relaxation." },
];

const Classes: React.FC = () => {
  const [courses, setCourses] = useState<Course[]>([]);
  const [selectedCourse, setSelectedCourse] = useState<Course | null>(null);

  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const res = await fetch("http://localhost:8000/api/courses");
        if (!res.ok) throw new Error(`Status ${res.status}`);
        const data = await res.json();
        setCourses(data);
      } catch (err) {
        console.error("Failed to fetch courses:", err);
      }
    };

    fetchCourses();
  }, []);

  return (
    <div className="classes-page">
      {/* === LEVELS === */}
      <section className="section">
        <h2 className="section-title">Levels</h2>
        <div className="cards-grid">
          {levels.map((level, idx) => (
            <div key={idx} className="card level-card">
              <h3>{level.name}</h3>
              <p>{level.description}</p>
              <div className="stars">
                {[...Array(level.stars)].map((_, i) => <span key={i}>★</span>)}
                {[...Array(5 - level.stars)].map((_, i) => <span key={i} style={{ opacity: 0.2 }}>★</span>)}
              </div>
            </div>
          ))}
        </div>
      </section>

      {/* === TYPES === */}
      <section className="section">
        <h2 className="section-title">Types of classes</h2>
        <div className="cards-grid">
          {classTypes.map((type, idx) => (
            <div key={idx} className="card">
              <h3>{type.name}</h3>
              <p>{type.description}</p>
            </div>
          ))}
        </div>
      </section>

      {/* === COURSES === */}
      <section className="section">
        <h2 className="section-title">Courses</h2>
        <div className="course-list">
          {courses.map((course, i) => (
            <div key={i} className="course-item">
              <div className="course-label">
                <span className="icon">🧘‍♀️</span> {course.title}
              </div>
              <div className="course-description">
                {course.yogaClass}, {course.level} Level <br />
                {course.instructor} | Duration: {course.duration}
              </div>
              <button
                className="details-button"
                onClick={() => setSelectedCourse(course)}
              >
                SEE DETAILS
              </button>
            </div>
          ))}
        </div>
      </section>

      {/* === MODAL === */}
      {selectedCourse && (
        <div className="modal-backdrop" onClick={() => setSelectedCourse(null)}>
          <div className="modal-content" onClick={(e) => e.stopPropagation()}>
            <h2>{selectedCourse.title}</h2>
            <p><strong>Style:</strong> {selectedCourse.yogaClass}</p>
            <p><strong>Level:</strong> {selectedCourse.level}</p>
            <p><strong>Instructor:</strong> {selectedCourse.instructor}</p>
            <p><strong>Duration:</strong> {selectedCourse.duration}</p>
            <button className="close-button" onClick={() => setSelectedCourse(null)}>Close</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Classes;
