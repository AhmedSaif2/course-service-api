package com.asaif.course_service.service;

import com.asaif.course_service.dto.CourseDto;
import com.asaif.course_service.mapper.CourseMapper;
import com.asaif.course_service.model.Rating;
import com.asaif.course_service.util.CourseRecommender;
import com.asaif.course_service.model.Course;
import com.asaif.course_service.repository.CourseRepository;
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
    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            return null;
        }
        return courseMapper.courseToDto(course);
    }
    public boolean createCourse(CourseDto courseDto) {
        Course course = courseMapper.dtoToCourse(courseDto);
        if (course.getRatings()!=null){
            for (Rating rating : course.getRatings()) {
                rating.setCourse(course);
            }
        }
        if (course.getAssessment()!=null){
            course.getAssessment().setCourse(course);
        }
        courseRepository.save(course);
        return true;
    }
    public boolean updateCourse(Long id,CourseDto courseDto){
        if (courseRepository.existsById(id)) {
            Course course = courseMapper.dtoToCourse(courseDto);
            course.setId(id);
            courseRepository.save(course);
            return true;
        }
        return false;
    }
    public boolean deleteCourse(Long id){
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
