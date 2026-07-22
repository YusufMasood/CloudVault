import { Link } from "react-router-dom";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../../services/authService";

export default function Login() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await loginUser(email, password);

            console.log(response.data);

            const data = response.data.data;

            localStorage.setItem("token", data.token);
            localStorage.setItem("name", data.name);
            localStorage.setItem("email", data.email);
            localStorage.setItem("role", data.role);

            alert("Login Successful!");

            navigate("/dashboard");

        } catch (error) {
            console.error(error);

            alert(
                error.response?.data?.message || "Login Failed"
            );
        }
    };

    return (
        <div className="min-h-screen bg-slate-950 flex items-center justify-center px-4">
            <div className="w-full max-w-md bg-slate-900 border border-slate-800 rounded-2xl shadow-2xl p-8">

                <div className="text-center mb-8">
                    <h1 className="text-4xl font-bold text-cyan-400">CloudVault</h1>
                    <p className="text-slate-400 mt-2">
                        Secure Cloud Storage
                    </p>
                </div>

                <form onSubmit={handleLogin} className="space-y-5">

                    <div>
                        <label className="block text-slate-300 mb-2">
                            Email
                        </label>
                        <input
                            type="email"
                            placeholder="Enter your email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            className="w-full rounded-lg bg-slate-800 border border-slate-700 p-3 text-white outline-none focus:border-cyan-400"
                        />
                    </div>

                    <div>
                        <label className="block text-slate-300 mb-2">
                            Password
                        </label>
                        <input
                            type="password"
                            placeholder="Enter your password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            className="w-full rounded-lg bg-slate-800 border border-slate-700 p-3 text-white outline-none focus:border-cyan-400"
                        />
                    </div>

                    <button
                        type="submit"
                        className="w-full bg-cyan-500 hover:bg-cyan-600 transition rounded-lg p-3 font-semibold text-white"
                    >
                        Login
                    </button>

                </form>

                <p className="text-center text-slate-400 mt-6">
                    Don't have an account?{" "}
                    <Link
                        to="/register"
                        className="text-cyan-400 hover:underline"
                    >
                        Register
                    </Link>
                </p>

            </div>
        </div>
    );
}