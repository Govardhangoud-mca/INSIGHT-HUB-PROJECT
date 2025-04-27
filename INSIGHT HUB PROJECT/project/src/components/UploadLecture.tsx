import { useState } from "react";
import { useNavigate } from "react-router-dom";

const UploadLecture = () => {
  const [unit, setUnit] = useState("Unit 1"); // Default selection
  const [title, setTitle] = useState("");
  const [instructor, setInstructor] = useState("");
  const [subject, setSubject] = useState("");
  const [videoUrl, setVideoUrl] = useState("");
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    // 🔹 Ensure all fields are filled
    if (!unit || !title || !instructor || !subject || !videoUrl) {
      setError("All fields are required.");
      return;
    }

    const lectureData = { unit, title, instructor, subject, videoUrl };

    try {
      const response = await fetch("http://localhost:8083/api/lectures", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(lectureData),
      });

      const responseData = await response.json().catch(() => null); // Try to parse JSON

      console.log("Server Response:", response.status, responseData);

      if (!response.ok) {
        throw new Error(
          responseData?.message || `Failed to upload lecture (Status: ${response.status})`
        );
      }

      // ✅ Navigate to Lecture List after successful upload
      navigate("/faculty-dashboard/lecturelist");
    } catch (err: any) {
      console.error("Upload error:", err);
      setError(err.message || "Failed to upload lecture");
    }
  };

  return (
    <div className="container mx-auto p-4">
      <h2 className="text-xl font-semibold mb-4">Upload Lecture</h2>
      {error && <p className="text-red-500">{error}</p>}

      <form onSubmit={handleSubmit} className="space-y-4">
        {/* 🔹 Unit Selection Dropdown (Placed First) */}
        <select
          value={unit}
          onChange={(e) => setUnit(e.target.value)}
          className="w-full p-2 border rounded-lg"
          required
        >
          <option value="Unit 1">Unit 1</option>
          <option value="Unit 2">Unit 2</option>
          <option value="Unit 3">Unit 3</option>
          <option value="Unit 4">Unit 4</option>
          <option value="Unit 5">Unit 5</option>
          <option value="Unit 6">Unit 6</option>
          <option value="Unit 7">Unit 7</option>
          <option value="Unit 8">Unit 8</option>
          <option value="Unit 9">Unit 9</option>
          <option value="Unit 10">Unit 10</option>
        </select>

        <input
          type="text"
          placeholder="Lecture Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          className="w-full p-2 border rounded-lg"
          required
        />
        <input
          type="text"
          placeholder="Instructor Name"
          value={instructor}
          onChange={(e) => setInstructor(e.target.value)}
          className="w-full p-2 border rounded-lg"
          required
        />
        <input
          type="text"
          placeholder="Subject"
          value={subject}
          onChange={(e) => setSubject(e.target.value)}
          className="w-full p-2 border rounded-lg"
          required
        />
        <input
          type="text"
          placeholder="Video URL"
          value={videoUrl}
          onChange={(e) => setVideoUrl(e.target.value)}
          className="w-full p-2 border rounded-lg"
          required
        />

        <button
          type="submit"
          className="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 transition"
        >
          Upload
        </button>
      </form>
    </div>
  );
};

export default UploadLecture;
