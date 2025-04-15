package com.asaif.course_service.util;

import com.asaif.course_service.model.Course;
import com.asaif.course_service.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class RecommendAll implements CourseRecommender {
    @Autowired
    CourseRepository courseRepository;
    @Override
    public List<Course> recommendCourses() {
        return (List<Course>)courseRepository.findAll();
    }

}