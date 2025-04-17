package com.asaif.course_service.service;

import com.asaif.course_service.dto.CourseDto;
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
    private final CourseMapper courseMapper;
    public CourseService(CourseRepository courseRepository,
                         CourseRecommender courseRecommender,
                         CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseRecommender = courseRecommender;
        this.courseMapper = courseMapper;
    }
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courseMapper.coursesToDtos(courses);
    }
    public CourseDto getCourseById(String id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            return null;
        }
        return courseMapper.courseToDto(course);
    }
    public Course createCourse(CourseDto courseDto) {
        return courseRepository.save(courseMapper.dtoToCourse(courseDto));
    }
    public boolean updateCourse(String id,CourseDto courseDto){
        if (courseRepository.existsById(id)) {
            Course course = courseMapper.dtoToCourse(courseDto);
            course.setId(id);
            courseRepository.save(course);
            return true;
        }
        return false;
    }
    public boolean deleteCourse(String id){
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<CourseDto> getRecommendedCourses(){
        List<Course> courses = courseRecommender.recommendCourses();
        return courseMapper.coursesToDtos(courses);
    }

    public List<CourseDto> getPagedCourses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseMapper.coursesToDtos(courseRepository.findAll(pageable).getContent());
    }
}
