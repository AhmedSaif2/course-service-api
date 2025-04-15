package com.asaif.course_service.controller;

import com.asaif.course_service.service.CourseService;
import com.asaif.course_service.model.Course;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    CourseService courseService;
    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping
    public Iterable<Course> getAll(){
        return courseService.getAllCourses();
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
    public void createCourse(@RequestBody Course course){
        courseService.createCourse(course);
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
