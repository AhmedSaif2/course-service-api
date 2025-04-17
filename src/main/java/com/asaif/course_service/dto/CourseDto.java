package com.asaif.course_service.dto;

import lombok.Getter;
import lombok.Setter;
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
