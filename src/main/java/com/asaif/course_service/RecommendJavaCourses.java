package com.asaif.course_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecommendJavaCourses implements CourseRecommender {
    @Autowired
    CourseRepository courseRepository;
    @Override
    public List<Course> recommendCourses() {
        List<Course> recommendedCourses = (List<Course>) courseRepository.findAll();
        recommendedCourses = recommendedCourses.stream()
                .filter(course -> course.getName().toLowerCase().contains("java"))
                .collect(Collectors.toList());
        return recommendedCourses;
    }

}