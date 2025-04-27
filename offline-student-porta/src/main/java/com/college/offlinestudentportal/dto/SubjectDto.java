package com.college.offlinestudentportal.dto;

public class SubjectDto {
    private String title;
    private String tutorName;
    private String department;
    private int semester;
    private double paymentAmount; // New field for payment

    // Constructors
    public SubjectDto() {}

    public SubjectDto(String title, String tutorName, String department, int semester, double paymentAmount) {
        this.title = title;
        this.tutorName = tutorName;
        this.department = department;
        this.semester = semester;
        this.paymentAmount = paymentAmount;
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getTutorName() { return tutorName; }
    public void setTutorName(String tutorName) { this.tutorName = tutorName; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }

    public double getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(double paymentAmount) { this.paymentAmount = paymentAmount; }
}
