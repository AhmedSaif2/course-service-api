package com.asaif.course_service.service;

import com.asaif.course_service.mapper.CourseMapper;
import com.asaif.course_service.util.CourseRecommender;
import com.asaif.course_service.model.Course;
import com.asaif.course_service.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseRecommender courseRecommender;
    public CourseService(CourseRepository courseRepository,
                         CourseRecommender courseRecommender,
                         CourseMapper courseMapper) {
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
        return courseRepository.save(course);
    }
    public void deleteCourse(String id){
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        }
    }
    public List<Course> getRecommendedCourses(){
        return courseRecommender.recommendCourses();
    }

    public Page<Course> getPagedCourses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseRepository.findAll(pageable);
    }
}
