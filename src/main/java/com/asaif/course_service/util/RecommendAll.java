package com.asaif.course_service.util;

import com.asaif.course_service.model.Course;
import com.asaif.course_service.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecommendAll implements CourseRecommender {
    @Autowired
    CourseRepository courseRepository;
    @Override
    public List<Course> recommendCourses() {
        // To Do: Implement a recommendation algorithm
        // For now, we will return all courses
        // This should request an external endpoint from a mock service and return an array of courses as xml data
        return (List<Course>)courseRepository.findAll();
    }

}