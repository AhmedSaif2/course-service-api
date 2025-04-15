package com.asaif.course_service.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String description;

    @OneToMany
    @JoinColumn(name = "rating_id", referencedColumnName = "id")
    private List<Rating> ratings;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assesment_id")
    private Assesment assesment;
    @ManyToMany
    @JoinTable(
            name = "course_author",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    public Course() {

    }
    public Course(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
