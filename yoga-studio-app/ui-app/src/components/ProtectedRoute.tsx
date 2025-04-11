import React from "react";
import { Navigate } from "react-router-dom";

interface ProtectedRouteProps {
  children: JSX.Element;
  roles?: string[];
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ children, roles }) => {
  const token = sessionStorage.getItem("accessToken");
  const userRole = sessionStorage.getItem("role");

  if (!token) {
    return <Navigate to="/login" />;
  }

  if (roles && !roles.includes(userRole || "")) {
    return <Navigate to="/schedule" />;
  }

  return children;
};

export default ProtectedRoute;
