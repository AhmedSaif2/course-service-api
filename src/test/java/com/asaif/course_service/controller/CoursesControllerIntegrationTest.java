package com.asaif.course_service.controller;

import com.asaif.course_service.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class CoursesControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CourseRepository courseRepository;
    private int initialCourseCount;

    @BeforeEach
    void setUp() {
        initialCourseCount = courseRepository.findAll().size();
    }

    @Test
    void getAllCourses_coursesExist_returnsCourses() throws Exception {
        mockMvc.perform(get("/courses").param("page", "0").param("size", "10").headers(new HttpHeaders() {{
            set("X-Validation-Report", "true");
        }})).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(initialCourseCount))).andExpect(jsonPath("$[0].name").value("Java Basics")).andExpect(jsonPath("$[1].name").value("Spring Boot")).andExpect(jsonPath("$[2].name").value(".Net"));
    }

    @Test
    void getCourseById_courseExists_returnsCourse() throws Exception {
        mockMvc.perform(get("/courses/{id}", "1").headers(new HttpHeaders() {{
            set("X-Validation-Report", "true");
        }})).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.name").value("Java Basics"));
    }

    @Test
    void getCourseById_courseDoesNotExist_returnsNotFound() throws Exception {
        mockMvc.perform(get("/courses/{id}", "999").headers(new HttpHeaders() {{
            set("X-Validation-Report", "true");
        }})).andExpect(status().isNotFound());
    }

    @Test
    void getRecommendedCourses_coursesExist_returnsRecommendedCourses() throws Exception {
        mockMvc.perform(get("/courses/recommended").headers(new HttpHeaders() {{
            set("X-Validation-Report", "true");
        }})).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].name").value("Java Basics"));
    }

    @Test
    void createCourse_validCourse_returnsCreatedCourse() throws Exception {
        String newCourseJson = "{ \"name\": \"Test Course\", \"description\": \"This is a Test Course\"}";
        mockMvc.perform(post("/courses").headers(new HttpHeaders() {{
                    set("X-Validation-Report", "true");
                }}).with(httpBasic("bob", "adminpass")).contentType(MediaType.APPLICATION_JSON).content(newCourseJson).headers(new HttpHeaders() {{
                    set("X-Validation-Report", "true");
                }})

        ).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.name").value("Test Course")).andExpect(jsonPath("$.description").value("This is a Test Course"));
        mockMvc.perform(get("/courses").param("page", "0").param("size", "10").headers(new HttpHeaders() {{
            set("X-Validation-Report", "true");
        }})).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(initialCourseCount + 1)));
    }

    @Test
    void updateCourse_courseExists_returnsUpdatedCourse() throws Exception {
        String updatedCourseJson = "{ \"name\": \"Updated Course\", \"description\": \"This is an Updated Course\"}";
        mockMvc.perform(put("/courses/{id}", "1").headers(new HttpHeaders() {{
            set("X-Validation-Report", "true");
        }}).with(httpBasic("bob", "adminpass")).contentType(MediaType.APPLICATION_JSON).content(updatedCourseJson)).andExpect(status().isOk());
        mockMvc.perform(get("/courses/{id}", "1").headers(new HttpHeaders() {{
            set("X-Validation-Report", "true");
        }})).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.name").value("Updated Course")).andExpect(jsonPath("$.description").value("This is an Updated Course"));
    }

    @Test
    void updateCourse_courseDoesNotExist_returnsNotFound() throws Exception {
        String updatedCourseJson = "{ \"name\": \"Updated Course\", \"description\": \"This is an Updated Course\"}";
        mockMvc.perform(put("/courses/{id}", "999").headers(new HttpHeaders() {{
            set("X-Validation-Report", "true");
        }}).with(httpBasic("bob", "adminpass")).contentType(MediaType.APPLICATION_JSON).content(updatedCourseJson)).andExpect(status().isNotFound());
    }

    @Test
    void deleteCourse_courseExists_returnsOk() throws Exception {
        mockMvc.perform(delete("/courses/{id}", "1").headers(new HttpHeaders() {{
            set("X-Validation-Report", "true");
        }}).with(httpBasic("bob", "adminpass"))).andExpect(status().isOk());
        mockMvc.perform(get("/courses").headers(new HttpHeaders() {{
            set("X-Validation-Report", "true");
        }}).param("page", "0").param("size", "10")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(initialCourseCount - 1)));
    }

    @Test
    void deleteCourse_courseDoesNotExist_returnsNotFound() throws Exception {
        mockMvc.perform(delete("/courses/{id}", "999").headers(new HttpHeaders() {{
            set("X-Validation-Report", "true");
        }}).with(httpBasic("bob", "adminpass"))).andExpect(status().isNotFound());
    }

}
