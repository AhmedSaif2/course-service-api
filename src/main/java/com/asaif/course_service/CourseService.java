package com.asaif.course_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseRecommender courseRecommender;
    public CourseService(CourseRepository courseRepository,
                         @Qualifier("recommendJavaCourses") CourseRecommender courseRecommender) {
        this.courseRepository = courseRepository;
        this.courseRecommender = courseRecommender;
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
    public List<Course> getRecommendedCourses(){
        return courseRecommender.recommendCourses();
    }

}
