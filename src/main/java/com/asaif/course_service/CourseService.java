package com.asaif.course_service;

import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public Iterable<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    public Course getCourseById(String id) {
        return courseRepository.findById(id).orElse(null);
    }
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
    public Course updateCourse(Course course){
        if (courseRepository.existsById(course.getId())) {
            return courseRepository.save(course);
        } else {
            return null;
        }
    }
    public void deleteCourse(String id){
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        }
    }

}
