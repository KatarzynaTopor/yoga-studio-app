import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./TeacherPanel.css";

interface Schedule {
  id: string;
  title: string;
  scheduleTime: string;
  instructorId: string;
}

interface Student {
  id: string;
  username: string;
  email: string;
}

const TeacherPanel: React.FC = () => {
  const navigate = useNavigate();
  const [schedules, setSchedules] = useState<Schedule[]>([]);
  const [students, setStudents] = useState<Student[]>([]);
  const [selectedSchedule, setSelectedSchedule] = useState<Schedule | null>(null);
  const [error, setError] = useState<string>("");

  const token = sessionStorage.getItem("accessToken");
  const instructorId = sessionStorage.getItem("instructorId");

  useEffect(() => {
    fetchSchedules();
  }, [instructorId, token]);

  const fetchSchedules = async () => {
    try {
      const res = await fetch(`http://localhost:8000/api/schedule`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      if (!res.ok) throw new Error("Failed to fetch schedules.");
      const data = await res.json();
      const filtered = data.filter((schedule: Schedule) => schedule.instructorId === instructorId);
      setSchedules(filtered);
    } catch (err: any) {
      console.error("Error fetching schedules:", err);
      setError(err.message || "Failed to fetch schedules.");
    }
  };

  const fetchStudents = async (schedule: Schedule) => {
    try {
      const res = await fetch(`http://localhost:8000/api/teacher/schedule/${schedule.id}/students`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      if (!res.ok) throw new Error("Failed to fetch students.");
      const data = await res.json();
      setStudents(data);
      setSelectedSchedule(schedule);
    } catch (err: any) {
      console.error("Error fetching students:", err);
      setError(err.message || "Failed to fetch students.");
    }
  };

  const deleteSchedule = async (id: string) => {
    if (!window.confirm("Are you sure you want to delete this schedule?")) return;

    try {
      const res = await fetch(`http://localhost:8000/api/schedule/${id}`, {
        method: "DELETE",
        headers: { Authorization: `Bearer ${token}` },
      });

      if (res.ok) {
        alert("Schedule deleted!");
        setSchedules((prev) => prev.filter((s) => s.id !== id));
        if (selectedSchedule?.id === id) {
          setSelectedSchedule(null);
          setStudents([]);
        }
      } else {
        throw new Error("Failed to delete schedule.");
      }
    } catch (err: any) {
      console.error("Error deleting schedule:", err);
      alert(err.message || "Failed to delete schedule.");
    }
  };

  const handleLogout = () => {
    sessionStorage.clear();
    navigate("/login");
  };

  return (
    <div className="teacher-panel">
      <h2 className="page-title">Teacher Panel</h2>

      <div className="panel-grid">
        {/* Left: Class List */}
        <div className="panel-left">
          <h3>Your Classes</h3>
          {schedules.length === 0 ? (
            <p className="empty-text">No schedules yet.</p>
          ) : (
            <div className="schedule-list">
              {schedules.map((schedule) => (
                <div
                  key={schedule.id}
                  className={`schedule-card ${selectedSchedule?.id === schedule.id ? "active" : ""}`}
                >
                  <div className="schedule-header">
                    <div onClick={() => fetchStudents(schedule)} className="schedule-info">
                      <h4>{schedule.title}</h4>
                      <p>{new Date(schedule.scheduleTime).toLocaleString()}</p>
                    </div>
                    <button
                      className="delete-button-full"
                      onClick={() => deleteSchedule(schedule.id)}
                    >
                      Delete
                    </button>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>

        {/* Right: Students */}
        <div className="panel-right">
          {selectedSchedule && (
            <>
              <h3>Students Registered</h3>
              <p className="students-count">{students.length} signed up</p>

              {error && <p className="error-text">{error}</p>}

              <div className="students-grid">
                {students.length > 0 ? (
                  students.map((student) => (
                    <div key={student.id} className="student-card">
                      <span className="student-name">{student.username}</span>
                      <span className="student-email">{student.email}</span>
                    </div>
                  ))
                ) : (
                  <p className="empty-text">No students yet.</p>
                )}
              </div>
            </>
          )}
        </div>
      </div>

      {/* Bottom Buttons */}
    <div className="bottom-buttons">
      <button onClick={() => navigate("/schedule/add")} className="add-schedule-button">
        Add New Schedule
      </button>

      <button onClick={() => {
        sessionStorage.clear();
        navigate("/login");
      }} className="logout-button">
        Sign Out
      </button>
    </div>

    </div>
  );
};

export default TeacherPanel;
