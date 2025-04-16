package com.asaif.course_service.controller;

import com.asaif.course_service.dto.CourseDto;
import com.asaif.course_service.mapper.CourseMapper;
import com.asaif.course_service.service.CourseService;
import com.asaif.course_service.model.Course;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CourseDto> getById(@PathVariable String id){
        Course course = courseService.getCourseById(id);
        if (course == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courseMapper.courseToDto(course));
    }
    @GetMapping("/recommended")
    public List<CourseDto> getRecommendedCourses(){
        return courseMapper.coursesToDtos(courseService.getRecommendedCourses());
    }
    @PostMapping
    public void createCourse(@RequestBody CourseDto courseDto){
        Course newCourse = courseMapper.dtoToCourse(courseDto);
        courseService.createCourse(newCourse);
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> updateCourse(@PathVariable String id,@RequestBody CourseDto courseDto){
        if (courseService.getCourseById(id) == null){
            return ResponseEntity.notFound().build();
        }
        Course course = courseMapper.dtoToCourse(courseDto);
        course.setId(id);
        courseService.updateCourse(course);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        Course course = courseService.getCourseById(id);
        if (course == null){
            return ResponseEntity.notFound().build();
        }
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }
}
