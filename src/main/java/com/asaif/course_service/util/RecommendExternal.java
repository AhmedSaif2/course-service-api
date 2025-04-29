package com.asaif.course_service.util;

import com.asaif.course_service.CoursesClient;
import com.asaif.course_service.dto.CourseDto;
import com.asaif.course_service.model.Course;
import generated.Courses;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class RecommendExternal implements CourseRecommender {
    private final CoursesClient coursesClient;

    public RecommendExternal(CoursesClient coursesClient) {
        this.coursesClient = coursesClient;
    }

    @Override
    public List<Course> recommendCourses() {
        String response = coursesClient.getCourses();
        try {
            Courses courses = CourseUnmarshaller.unmarshalCourses(response);
            return courses.getCourse().stream()
                    .map(course -> new Course(
                            course.getId(),
                            course.getName(),
                            course.getDescription(),
                            null, null, null
                    ))
                    .toList();
        } catch (Exception exception) {
            return List.of();
        }
    }
}
