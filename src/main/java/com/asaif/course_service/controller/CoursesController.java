package com.asaif.course_service.controller;

import com.asaif.course_service.Dto.CourseDto;
import com.asaif.course_service.mapper.CourseMapper;
import com.asaif.course_service.service.CourseService;
import com.asaif.course_service.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    private CourseService courseService;
    private CourseMapper courseMapper;
    public CoursesController(CourseService courseService,
                             CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }
    @GetMapping
    public List<CourseDto> getAll(@RequestParam int page, @RequestParam int size){
        return courseMapper.coursesToDtos(courseService.getPagedCourses(page, size).getContent());
    }
    @GetMapping("{id}")
    public Course getById(@PathVariable String id){
        return courseService.getCourseById(id);
    }
    @GetMapping("/recommend")
    public List<Course> getRecommendedCourses(){
        return courseService.getRecommendedCourses();
    }
    @PostMapping
    public void createCourse(@RequestBody CourseDto course){
        Course newCourse = courseMapper.dtoToCourse(course);
        courseService.createCourse(newCourse);
    }
    @PutMapping("{id}")
    public void updateCourse(@PathVariable String id,@RequestBody Course course){
        courseService.updateCourse(course);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id){
        courseService.deleteCourse(id);
    }
}
