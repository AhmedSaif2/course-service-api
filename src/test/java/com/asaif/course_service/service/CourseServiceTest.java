package com.asaif.course_service.service;

import com.asaif.course_service.dto.CourseDto;
import com.asaif.course_service.mapper.CourseMapper;
import com.asaif.course_service.model.Course;
import com.asaif.course_service.repository.CourseRepository;
import com.asaif.course_service.util.CourseRecommender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CourseMapper courseMapper;
    @Mock
    private CourseRecommender courseRecommender;
    @InjectMocks
    private CourseService courseService;
    private final String wrongId = "999";
    private Course testCourse;
    private CourseDto testCourseDto;
    @BeforeEach
    void setUp(){
        testCourseDto = new CourseDto();
        testCourseDto.setName("Test Course");
        testCourseDto.setDescription("This is a Test Course");

        testCourse = new Course();
        testCourse.setId("1");
        testCourse.setName("Test Course");
        testCourse.setDescription("This is a Test Course");
    }

    @Test
    void getCourseById_existingId_returnsCorrectCourse(){
        when(courseRepository.findById(testCourse.getId())).thenReturn(Optional.of(testCourse));
        when(courseMapper.courseToDto(testCourse)).thenReturn(testCourseDto);

        CourseDto result = courseService.getCourseById(testCourse.getId());

        assertNotNull(result);
        assertEquals(testCourse.getName(),result.getName());
        assertEquals(testCourse.getDescription(),result.getDescription());
    }
    @Test
    void getCourseById_nonExistingId_returnsNull(){
        when(courseRepository.findById(wrongId)).thenReturn(Optional.empty());

        CourseDto result = courseService.getCourseById(wrongId);

        assertNull(result);
    }
    @Test
    void createCourse_Course_returnsCreatedCourse(){
        CourseDto inputCourse = new CourseDto();
        inputCourse.setName("new Course");
        inputCourse.setDescription("This is a new Course");

        Course createdCourse = new Course();
        createdCourse.setId("2");
        createdCourse.setName(inputCourse.getName());
        createdCourse.setDescription(inputCourse.getDescription());

        when(courseMapper.dtoToCourse(inputCourse)).thenReturn(createdCourse);
        when(courseRepository.save(createdCourse)).thenReturn(createdCourse);

        Course result = courseService.createCourse(inputCourse);

        assertEquals(createdCourse.getId(),result.getId());
        assertEquals(createdCourse.getName(),result.getName());
        assertEquals(createdCourse.getDescription(),result.getDescription());
    }
    @Test
    void updateCourse_existingId_updatesCourse(){
        String id = "1";
        CourseDto updatedCourseDto = new CourseDto();
        updatedCourseDto.setName("Updated Course");
        updatedCourseDto.setDescription("This is an Updated Course");

        Course updatedCourse = new Course();
        updatedCourse.setId(id);
        updatedCourse.setName(updatedCourseDto.getName());
        updatedCourse.setDescription(updatedCourseDto.getDescription());

        when(courseRepository.existsById(id)).thenReturn(true);
        when(courseMapper.dtoToCourse(updatedCourseDto)).thenReturn(updatedCourse);
        when(courseRepository.save(updatedCourse)).thenReturn(updatedCourse);

        boolean result = courseService.updateCourse(id,updatedCourseDto);

        assertTrue(result);
    }
    @Test
    void updateCourse_nonExistingId_doesNotUpdate(){
        CourseDto updatedCourseDto = new CourseDto();
        updatedCourseDto.setName("Updated Course");
        updatedCourseDto.setDescription("This is an Updated Course");

        when(courseRepository.existsById(wrongId)).thenReturn(false);

        boolean result = courseService.updateCourse(wrongId,updatedCourseDto);

        assertFalse(result);
    }
    @Test
    void deleteCourse_existingId_deletesCourse(){
        when(courseRepository.existsById(testCourse.getId())).thenReturn(true);

        boolean result = courseService.deleteCourse(testCourse.getId());

        assertTrue(result);
    }
    @Test
    void deleteCourse_nonExistingId_doesNotDelete(){
        when(courseRepository.existsById(wrongId)).thenReturn(false);

        boolean result = courseService.deleteCourse(wrongId);

        assertFalse(result);
    }
    @Test
    void getRecommendedCourses_coursesExist_returnsRecommendedCourses(){
        List<Course> courses = new ArrayList<>();
        courses.add(testCourse);

        when(courseRecommender.recommendCourses()).thenReturn(courses);
        when(courseMapper.coursesToDtos(courses)).thenReturn(List.of(testCourseDto));

        List<CourseDto> result=courseService.getRecommendedCourses();

        assertNotNull(result);
        assertEquals(1,result.size());
        assertEquals(testCourseDto.getName(), result.get(0).getName());
        assertEquals(testCourseDto.getDescription(), result.get(0).getDescription());
    }
    @Test
    void getRecommendedCourses_noCourses_returnsEmptyList(){
        List<Course> courses = new ArrayList<>();

        when(courseRecommender.recommendCourses()).thenReturn(courses);
        when(courseMapper.coursesToDtos(courses)).thenReturn(new ArrayList<>());

        List<CourseDto> result=courseService.getRecommendedCourses();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    void getPagedCourses_coursesExist_returnsPagedCourses(){
        List<Course> courses = new ArrayList<>();
        courses.add(testCourse);
        int page = 0;
        int size = 1;
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursePage = new PageImpl<>(courses, pageable, courses.size());

        when(courseRepository.findAll(pageable)).thenReturn(coursePage);
        when(courseMapper.coursesToDtos(courses)).thenReturn(List.of(testCourseDto));

        List<CourseDto> result=courseService.getPagedCourses(page,size);

        assertNotNull(result);
        assertEquals(1,result.size());
        assertEquals(testCourseDto.getName(), result.get(0).getName());
        assertEquals(testCourseDto.getDescription(), result.get(0).getDescription());
    }
    @Test
    void getPagedCourses_noCourses_returnsEmptyList(){
        List<Course> courses = new ArrayList<>();
        int page = 0;
        int size = 1;
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> emptyPage = Page.empty(pageable);

        when(courseRepository.findAll(pageable)).thenReturn(emptyPage);
        when(courseMapper.coursesToDtos(courses)).thenReturn(new ArrayList<>());

        List<CourseDto> result=courseService.getPagedCourses(page,size);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
