package com.asaif.course_service.util;

import com.asaif.course_service.model.Course;
import com.asaif.course_service.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecommendAllTest {
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private RecommendAll recommendAll;
    private Course testCourse = new Course();
    @BeforeEach
    public void setUp() {
        testCourse.setId("1");
        testCourse.setName("test Course");
        testCourse.setDescription("test Description");
    }
    @Test
    public void recommendCourse_coursesExist_returnsCourses() {
        when(courseRepository.findAll()).thenReturn(List.of(testCourse));

        List<Course> result = recommendAll.recommendCourses();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testCourse, result.get(0));
    }
    @Test
    public void recommendCourse_noCoursesExist_returnsEmptyList() {
        when(courseRepository.findAll()).thenReturn(List.of());

        List<Course> result = recommendAll.recommendCourses();

        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
