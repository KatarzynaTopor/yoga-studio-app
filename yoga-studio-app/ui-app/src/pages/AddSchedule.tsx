import React, { useState } from "react";

const API_URL = import.meta.env.VITE_BACKEND_URL || "http://localhost:8000/api/schedule";

const AddSchedule = () => {
    const [formData, setFormData] = useState({
        title: "",
        description: "",
        instructor: "",
        scheduleTime: "",
        capacity: 10
    });

    const [success, setSuccess] = useState(false);
    const [error, setError] = useState("");

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");
        setSuccess(false);

        try {
            const response = await fetch(API_URL, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ ...formData, capacity: Number(formData.capacity) })
            });

            if (response.ok) {
                setSuccess(true);
                setFormData({ title: "", description: "", instructor: "", scheduleTime: "", capacity: 10 });
            } else {
                setError("Failed to add schedule.");
            }
        } catch (err) {
            setError("Something went wrong.");
        }
    };

    return (
        <div className="auth-container">
            <div className="auth-box">
                <h2>Add New Schedule</h2>
                <form onSubmit={handleSubmit} className="auth-form">
                    <input name="title" placeholder="Title" value={formData.title} onChange={handleChange} required />
                    <textarea name="description" placeholder="Description" value={formData.description} onChange={handleChange} required />
                    <input name="instructor" placeholder="Instructor" value={formData.instructor} onChange={handleChange} required />
                    <input type="datetime-local" name="scheduleTime" value={formData.scheduleTime} onChange={handleChange} required />
                    <input type="number" name="capacity" placeholder="Capacity" value={formData.capacity} onChange={handleChange} required />

                    {error && <p className="auth-error">{error}</p>}
                    {success && <p style={{ color: "green" }}>Schedule added!</p>}

                    <button type="submit" className="auth-button">Add Schedule</button>
                </form>
            </div>
        </div>
    );
};

export default AddSchedule;
