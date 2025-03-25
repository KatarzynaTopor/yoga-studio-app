import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./LoginRegister.css";

// ✅ FIX: Use `import.meta.env` for Vite compatibility
const API_BASE_URL = import.meta.env.VITE_BACKEND_URL || "http://localhost:8000/api/auth";

interface LoginRegisterProps {
    setIsAuthenticated: (value: boolean) => void;
}

const LoginRegister: React.FC<LoginRegisterProps> = ({ setIsAuthenticated }) => {
    const [isLogin, setIsLogin] = useState(true);
    const [formData, setFormData] = useState({
        username: "",
        email: "",
        password: "",
        confirmPassword: "",
    });
    const [error, setError] = useState("");
    const navigate = useNavigate();

    // ✅ Handle input change
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    // ✅ Handle form submission
    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");

        // ✅ Validate password confirmation
        if (!isLogin && formData.password !== formData.confirmPassword) {
            setError("Passwords do not match.");
            return;
        }

        const endpoint = isLogin ? `${API_BASE_URL}/login` : `${API_BASE_URL}/register`;
        const body = isLogin
            ? { username: formData.username, password: formData.password }
            : { username: formData.username, email: formData.email, password: formData.password };

        try {
            const response = await fetch(endpoint, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(body),
            });

            const data = await response.json();

            if (response.ok) {
                if (isLogin) {
                    // ✅ Use localStorage for persistent login state
                    localStorage.setItem("accessToken", data.token);
                    setIsAuthenticated(true);
                    navigate("/"); // ✅ Redirect to home page after login
                } else {
                    alert("Registered successfully! You can now log in.");
                    setIsLogin(true);
                }
            } else {
                setError(data.message || "An error occurred. Please check your input.");
            }
        } catch (err) {
            console.error("Error during fetch:", err);
            setError("Something went wrong. Please try again.");
        }
    };

    return (
        <div className="auth-container">
            <div className="auth-box">
                <div className="auth-tabs">
                    <button className={`auth-tab ${isLogin ? "active" : ""}`} onClick={() => setIsLogin(true)}>
                        Log in
                    </button>
                    <button className={`auth-tab ${!isLogin ? "active" : ""}`} onClick={() => setIsLogin(false)}>
                        Register
                    </button>
                </div>
                <form onSubmit={handleSubmit} className="auth-form">
                    <div className="input-container">
                        <input type="text" name="username" placeholder="Username" value={formData.username} onChange={handleChange} required />
                    </div>
                    {!isLogin && (
                        <div className="input-container">
                            <input type="email" name="email" placeholder="E-mail" value={formData.email} onChange={handleChange} required />
                        </div>
                    )}
                    <div className="input-container">
                        <input type="password" name="password" placeholder="Password" value={formData.password} onChange={handleChange} required />
                    </div>
                    {!isLogin && (
                        <div className="input-container">
                            <input type="password" name="confirmPassword" placeholder="Confirm Password" value={formData.confirmPassword} onChange={handleChange} required />
                        </div>
                    )}
                    {error && <p className="auth-error">{error}</p>}
                    <button type="submit" className="auth-button">
                        {isLogin ? "Log in" : "Register"}
                    </button>
                </form>
            </div>
        </div>
    );
};

export default LoginRegister;
