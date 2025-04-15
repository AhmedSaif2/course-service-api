package com.asaif.course_service.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    @ManyToMany(mappedBy = "authors")
    private List<Course> courses;

}
