package com.asaif.course_service.model;

import jakarta.persistence.*;

@Entity
public class Assesment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String content;
    @OneToOne(mappedBy = "assesment")
    private Course course;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
