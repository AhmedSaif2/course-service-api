package com.asaif.course_service.model;

import jakarta.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Id;
    private int number;
    @ManyToOne
    private Course course;

    public Rating() {
    }
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
