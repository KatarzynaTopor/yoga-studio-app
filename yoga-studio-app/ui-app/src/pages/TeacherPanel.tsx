import React, { useEffect, useState } from "react";

const TeacherPanel: React.FC = () => {
  const [students, setStudents] = useState<any[]>([]);
  const [scheduleId, setScheduleId] = useState<string>("");
  const [error, setError] = useState<string>("");

  const token = sessionStorage.getItem("accessToken");

  useEffect(() => {
    if (scheduleId) {
      fetch(`http://localhost:8000/api/schedule/${scheduleId}/students`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
        .then((res) => {
          if (!res.ok) {
            throw new Error("Failed to fetch students.");
          }
          return res.json();
        })
        .then((data) => setStudents(data))
        .catch((err) => setError(err.message));
    }
  }, [scheduleId]);

  const handleScheduleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setScheduleId(event.target.value);
  };

  return (
    <div>
      <h2>Teacher Panel</h2>

      <div>
        <label htmlFor="scheduleId">Schedule ID:</label>
        <input
          type="text"
          id="scheduleId"
          value={scheduleId}
          onChange={handleScheduleChange}
        />
      </div>

      <div>
        {error && <p className="error">{error}</p>}
        <h3>Students Registered</h3>
        {students.length > 0 ? (
          <ul>
            {students.map((student) => (
              <li key={student.id}>
                {student.username} ({student.email})
              </li>
            ))}
          </ul>
        ) : (
          <p>No students registered for this schedule.</p>
        )}
      </div>
    </div>
  );
};

export default TeacherPanel;
