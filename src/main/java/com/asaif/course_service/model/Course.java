package com.asaif.course_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Rating> ratings;
    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
    private Assessment assessment;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_author",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;
}
