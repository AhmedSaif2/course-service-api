package com.asaif.course_service.util;

import com.asaif.course_service.model.Course;

import java.util.List;

public interface CourseRecommender {
    List<Course> recommendCourses();
}