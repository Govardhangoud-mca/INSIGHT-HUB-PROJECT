import React, { useState, useEffect } from "react";
import { PlusCircle, Edit, Trash2, Book, CreditCard } from "lucide-react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { motion } from "framer-motion";
import { Department, Subject } from "../types";
import { SearchFilters } from "../components/SearchFilters";
import { AddSubjectModal } from "../components/AddSubjectModal";

const StudentDashboard: React.FC = () => {
  const [department, setDepartment] = useState<Department | ''>('');
  const [semester, setSemester] = useState<number | ''>('');
  const [searchQuery, setSearchQuery] = useState("");
  const [subjects, setSubjects] = useState<Subject[]>([]);
  const [filteredSubjects, setFilteredSubjects] = useState<Subject[]>([]);
  const [isSubjectModalOpen, setIsSubjectModalOpen] = useState(false);
  const [selectedSubject, setSelectedSubject] = useState<Subject | null>(null);
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    const script = document.createElement("script");
    script.src = "https://checkout.razorpay.com/v1/checkout.js";
    script.async = true;
    document.body.appendChild(script);
  }, []);

  useEffect(() => {
    fetchSubjects();
  }, []);

  useEffect(() => {
    let filtered = subjects;
    if (department) {
      filtered = filtered.filter((sub) => sub.department === department);
    }
    if (semester) {
      filtered = filtered.filter((sub) => sub.semester === semester);
    }
    if (searchQuery) {
      filtered = filtered.filter((sub) =>
        sub.title.toLowerCase().includes(searchQuery.toLowerCase())
      );
    }
    setFilteredSubjects(filtered);
  }, [department, semester, searchQuery, subjects]);

  const fetchSubjects = async () => {
    try {
      const response = await axios.get("http://localhost:8083/api/faculty/subjects/all");
      setSubjects(response.data);
      setFilteredSubjects(response.data);
      setError(null);
    } catch (err) {
      console.error("Error fetching subjects:", err);
      setError("Failed to load subjects. Please try again.");
    }
  };

  const handlePayment = () => {
    const options = {
      key: "rzp_test_xanrMICii3vWPe",
      amount: 50000,
      currency: "INR",
      name: "Insight Hub",
      description: "Payment for Subject",
      image: "/vite.svg",
      handler: function (response: any) {
        alert(`Payment Successful! Payment ID: ${response.razorpay_payment_id}`);
      },
      prefill: {
        name: "John Doe",
        email: "john@example.com",
        contact: "9876543210",
      },
      theme: {
        color: "#6B46C1",
      },
    };

    const razorpay = new (window as any).Razorpay(options);
    razorpay.open();
  };

  return (
    <div className="p-6 bg-gradient-to-r from-blue-500 to-purple-600 min-h-screen text-white">
      <h1 className="text-3xl font-extrabold mb-6 text-center">Student Dashboard</h1>
      <SearchFilters
        department={department}
        semester={semester}
        searchQuery={searchQuery}
        onDepartmentChange={setDepartment}
        onSemesterChange={setSemester}
        onSearchChange={setSearchQuery}
      />
      <div className="mt-6 bg-white p-6 rounded-xl shadow-xl text-gray-900">
        {filteredSubjects.length > 0 ? (
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
            {filteredSubjects.map((subject) => (
              <motion.div key={subject.id} className="p-6 border rounded-lg shadow-md bg-gray-50"
                whileHover={{ scale: 1.05 }}>
                <h2 className="text-xl font-bold text-gray-800">{subject.title}</h2>
                <p className="text-gray-600">Tutor: {subject.tutorName}</p>
                <p className="text-gray-500">
                  Department: {subject.department} | Semester: {subject.semester}
                </p>
                <div className="mt-4 flex gap-4">
                  <button
                    className="flex items-center gap-2 bg-blue-500 text-white px-4 py-2 rounded-lg shadow-lg hover:bg-blue-600 transition"
                    onClick={() => navigate("/student-dashboard/lecturelist2")}
                  >
                    <Book className="w-4 h-4" /> Units
                  </button>
                  <button
                    className="flex items-center gap-2 bg-purple-500 text-white px-4 py-2 rounded-lg shadow-lg hover:bg-purple-600 transition"
                    onClick={handlePayment}
                  >
                    <CreditCard className="w-4 h-4" /> Pay
                  </button>
                </div>
              </motion.div>
            ))}
          </div>
        ) : (
          <p className="text-gray-500 text-center">No subjects found.</p>
        )}
      </div>
    </div>
  );
};

export default StudentDashboard;
