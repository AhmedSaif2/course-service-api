package com.asaif.course_service.dto;

import com.asaif.course_service.model.Rating;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SecondaryRow;

import java.util.List;

@Getter
@Setter
public class CourseDto {
    private String name;
    private String description;
    private List<RatingDto> ratings;
    private List<AuthorDto> authors;
    private AssessmentDto assessment;
}
