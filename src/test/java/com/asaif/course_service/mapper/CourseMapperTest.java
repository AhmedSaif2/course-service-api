package com.asaif.course_service.mapper;

import com.asaif.course_service.dto.AssessmentDto;
import com.asaif.course_service.dto.AuthorDto;
import com.asaif.course_service.dto.CourseDto;
import com.asaif.course_service.dto.RatingDto;
import com.asaif.course_service.model.Assessment;
import com.asaif.course_service.model.Author;
import com.asaif.course_service.model.Course;
import com.asaif.course_service.model.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CourseMapperTest {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    void courseToDto_withNestedFields_returnsDto() {
        Course course = new Course();
        course.setId("1");
        course.setName("AI Basics");
        course.setDescription("AI Basics Course");

        Rating rating = new Rating("1",5,course);

        Author author = new Author("1", "Jane Doe","test@mail.com",List.of(course));
        Assessment assessment = new Assessment("1", "test assessment",course);

        course.setRatings(List.of(rating));
        course.setAuthors(List.of(author));
        course.setAssessment(assessment);

        CourseDto result = courseMapper.courseToDto(course);

        assertEquals(course.getName(), result.getName());
        assertEquals(course.getDescription(), result.getDescription());
        assertEquals(1, result.getRatings().size());
        assertEquals(1, result.getAuthors().size());
        assertEquals("test assessment", result.getAssessment().getContent());
    }
    @Test
    void dtoToCourse_withNestedFields_returnsCourse(){
        AssessmentDto assessmentDto = new AssessmentDto("test assessment");
        RatingDto ratingDto = new RatingDto( 5);
        AuthorDto authorDto = new AuthorDto("Jhon Doe","test@mail.com");
        CourseDto courseDto = new CourseDto("AI Basics", "AI Basics Course", List.of(ratingDto), List.of(authorDto), assessmentDto);

        Course result = courseMapper.dtoToCourse(courseDto);

        assertThat(result).isNotNull();
        assertEquals(courseDto.getName(),result.getName());
        assertEquals(courseDto.getDescription(),result.getDescription());
        assertEquals(1, result.getRatings().size());
        assertEquals(1, result.getAuthors().size());
        assertEquals("test assessment", result.getAssessment().getContent());
        assertEquals(5, result.getRatings().get(0).getNumber());
        assertEquals("Jhon Doe", result.getAuthors().get(0).getName());

    }
}
