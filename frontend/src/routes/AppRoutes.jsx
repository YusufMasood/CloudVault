import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Login from "../pages/Login/Login";


const Register = () => <h1 className="text-white text-4xl">Register</h1>;

export default function AppRoutes() {
    return (
        <BrowserRouter>
            <div className="min-h-screen bg-slate-950">
                <Routes>
                    <Route path="/" element={<Navigate to="/login" />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                </Routes>
            </div>
        </BrowserRouter>
    );
}