package com.college.offlinestudentportal.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "study_materials")
public class StudyMaterial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String subject;
    private String type;
    private String size;
    private int pages;
    private String author;
    private String lastUpdated;
    private String downloadUrl;

    // Default Constructor
    public StudyMaterial() {
    }

    // Parameterized Constructor
    public StudyMaterial(Long id, String title, String subject, String type, String size, int pages, String author, String lastUpdated, String downloadUrl) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.type = type;
        this.size = size;
        this.pages = pages;
        this.author = author;
        this.lastUpdated = lastUpdated;
        this.downloadUrl = downloadUrl;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
