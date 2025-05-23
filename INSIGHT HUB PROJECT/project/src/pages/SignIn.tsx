import React, { useState } from "react";
import axios, { AxiosError } from "axios";
import { LockClosedIcon, EnvelopeIcon } from "@heroicons/react/24/solid";
import { useNavigate } from "react-router-dom";

const SignIn = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate(); // Initialize useNavigate

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError("");

    try {
      // 🔹 API Request to Sign In
      const response = await axios.post(
        "http://localhost:8083/api/auth/login",
        { email, password },
        { withCredentials: true } // Include session cookies
      );

      console.log("✅ Sign-in successful:", response.data);

      // 🔹 Extract user role
      const { role } = response.data;

      // 🔹 Store token & role in localStorage
      localStorage.setItem("token", response.data.token);
      localStorage.setItem("userRole", role);

      // 🔹 Redirect based on role
      if (role === "ADMIN") {
        navigate("/admin-dashboard");
      } else if (role === "FACULTY") {
        navigate("/faculty-dashboard");
      } else if (role === "STUDENT") {
        navigate("/student-dashboard");
      } else {
        setError("Invalid role. Please contact support.");
      }
    } catch (err: AxiosError<{ message?: string }>) {
      setError(
        err.response?.data?.message || err.message || "Sign-in failed. Please try again."
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-50 to-indigo-100 py-8">
      <div className="bg-white p-8 rounded-2xl shadow-2xl w-full max-w-md transform transition-all duration-300 hover:scale-105">
        <h2 className="text-3xl font-bold text-center text-gray-900 mb-8">Sign In</h2>
        <form onSubmit={handleSubmit} className="space-y-6">
          {/* Email Field */}
          <div>
            <label htmlFor="email" className="block text-sm font-medium text-gray-700">
              Email
            </label>
            <div className="mt-1 relative rounded-md shadow-sm">
              <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                <EnvelopeIcon className="h-5 w-5 text-gray-400" aria-hidden="true" />
              </div>
              <input
                type="email"
                id="email"
                name="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
                className="block w-full pl-10 pr-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500"
                placeholder="you@example.com"
              />
            </div>
          </div>

          {/* Password Field */}
          <div>
            <label htmlFor="password" className="block text-sm font-medium text-gray-700">
              Password
            </label>
            <div className="mt-1 relative rounded-md shadow-sm">
              <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                <LockClosedIcon className="h-5 w-5 text-gray-400" aria-hidden="true" />
              </div>
              <input
                type="password"
                id="password"
                name="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                className="block w-full pl-10 pr-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500"
                placeholder="********"
              />
            </div>
          </div>

          {/* Error Message */}
          {error && <div className="text-red-500 text-sm text-center">{error}</div>}

          {/* Sign In Button */}
          <div>
            <button
              type="submit"
              disabled={loading}
              className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 transition-all duration-300"
            >
              {loading ? "Signing In..." : "Sign In"}
            </button>
          </div>

          {/* Sign Up Link */}
          <div className="text-center text-sm text-gray-600">
            Don't have an account?{" "}
            <a href="/signup" className="font-medium text-indigo-600 hover:text-indigo-500">
              Sign Up
            </a>
          </div>
        </form>
      </div>
    </div>
  );
};

export default SignIn;
