package com.asaif.course_service.controller;

import com.asaif.course_service.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class CoursesControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CourseRepository courseRepository;
    private int initialCourseCount;
    @BeforeEach
    public void setUp() {
        initialCourseCount = courseRepository.findAll().size();
    }
    @Test
    public void getAllCourses_coursesExist_returnsCourses() throws Exception {
        mockMvc.perform(get("/courses")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(initialCourseCount)))
                .andExpect(jsonPath("$[0].name").value("Java Basics"))
                .andExpect(jsonPath("$[1].name").value("Spring Boot"))
                .andExpect(jsonPath("$[2].name").value(".Net"));
    }
    @Test
    public void getCourseById_courseExists_returnsCourse() throws Exception {
        mockMvc.perform(get("/courses/{id}", "0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Java Basics"));
    }
    @Test
    public void getCourseById_courseDoesNotExist_returnsNotFound() throws Exception {
        mockMvc.perform(get("/courses/{id}", "999"))
                .andExpect(status().isNotFound());
    }
    @Test
    public void getRecommendedCourses_coursesExist_returnsRecommendedCourses() throws Exception {
        mockMvc.perform(get("/courses/recommended"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Java Basics"));
    }
    @Test
    public void createCourse_validCourse_returnsCreatedCourse() throws Exception {
        String newCourseJson = "{ \"name\": \"Test Course\", \"description\": \"This is a Test Course\"}";
        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newCourseJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test Course"))
                .andExpect(jsonPath("$.description").value("This is a Test Course"));
        mockMvc.perform(get("/courses")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(initialCourseCount + 1)))
                .andExpect(jsonPath("$[4].name").value("Test Course"));
    }
    @Test
    public void updateCourse_courseExists_returnsUpdatedCourse() throws Exception {
        String updatedCourseJson = "{ \"name\": \"Updated Course\", \"description\": \"This is an Updated Course\"}";
        mockMvc.perform(put("/courses/{id}", "0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedCourseJson))
                .andExpect(status().isOk());
        mockMvc.perform(get("/courses/{id}", "0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Updated Course"))
                .andExpect(jsonPath("$.description").value("This is an Updated Course"));
    }
    @Test
    public void updateCourse_courseDoesNotExist_returnsNotFound() throws Exception {
        String updatedCourseJson = "{ \"name\": \"Updated Course\", \"description\": \"This is an Updated Course\"}";
        mockMvc.perform(put("/courses/{id}", "999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedCourseJson))
                .andExpect(status().isNotFound());
    }
    @Test
    public void deleteCourse_courseExists_returnsOk() throws Exception {
        mockMvc.perform(delete("/courses/{id}", "0"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/courses")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(initialCourseCount - 1)));
    }
    @Test
    public void deleteCourse_courseDoesNotExist_returnsNotFound() throws Exception {
        mockMvc.perform(delete("/courses/{id}", "999"))
                .andExpect(status().isNotFound());
    }

}
