package com.asaif.course_service.service;

import com.asaif.course_service.model.Course;
import com.asaif.course_service.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private CourseService courseService;
    private final String wrongId = "999";
    private Course testCourse;
    @BeforeEach
    void setUp(){
        testCourse = new Course();
        testCourse.setId("1");
        testCourse.setName("Test Course");
        testCourse.setDescription("This is a Test Course");
    }

    @Test
    void getCourseById_existingId_returnsCorrectCourse(){
        when(courseRepository.findById(testCourse.getId())).thenReturn(Optional.of(testCourse));

        Course result = courseService.getCourseById(testCourse.getId());

        assertNotNull(result);
        assertEquals(testCourse.getId(),result.getId());
        assertEquals(testCourse.getName(),result.getName());
        assertEquals(testCourse.getDescription(),result.getDescription());
    }
    @Test
    void getCourseById_nonExistingId_returnsNull(){
        when(courseRepository.findById(wrongId)).thenReturn(Optional.empty());

        Course result = courseService.getCourseById(wrongId);

        assertNull(result);
    }
    @Test
    void getAllCourse_coursesExist_returnsCourses(){
        List<Course> courses = new ArrayList<>();
        courses.add(testCourse);

        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> result=courseService.getAllCourses();

        assertNotNull(result);
        assertEquals(1,result.size());
        assertEquals(testCourse.getId(), result.get(0).getId());
    }
    @Test
    void createCourse_Course_returnsCreatedCourse(){
        Course inputCourse = new Course();
        inputCourse.setName("new Course");
        inputCourse.setDescription("This is a new Course");

        Course createdCourse = new Course();
        createdCourse.setId("2");
        createdCourse.setName(inputCourse.getName());
        createdCourse.setDescription(inputCourse.getDescription());
        when(courseRepository.save(inputCourse)).thenReturn(createdCourse);

        Course result = courseService.createCourse(inputCourse);

        assertEquals(createdCourse.getId(),result.getId());
        assertEquals(createdCourse.getName(),result.getName());
        assertEquals(createdCourse.getDescription(),result.getDescription());
    }
}
