package com.asaif.course_service.controller;

import com.asaif.course_service.dto.CourseDto;
import com.asaif.course_service.service.CourseService;
import com.asaif.course_service.model.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    private final CourseService courseService;

    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping
    public List<CourseDto> getAllCourses(@RequestParam int page, @RequestParam int size){
        return courseService.getPagedCourses(page, size);
    }
    @GetMapping("{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable String id){
        CourseDto courseDto = courseService.getCourseById(id);
        if (courseDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courseDto);
    }
    @GetMapping("/recommended")
    public List<CourseDto> getRecommendedCourses(){
        return courseService.getRecommendedCourses();
    }
    @PostMapping
    public Course createCourse(@RequestBody CourseDto courseDto){
        return courseService.createCourse(courseDto);
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> updateCourse(@PathVariable String id,@RequestBody CourseDto courseDto){
        boolean updated = courseService.updateCourse(id,courseDto);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id){
        boolean deleted = courseService.deleteCourse(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
