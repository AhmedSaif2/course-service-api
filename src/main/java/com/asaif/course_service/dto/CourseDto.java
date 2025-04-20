package com.asaif.course_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDto {
    private String name;
    private String description;
    private List<RatingDto> ratings;
    private List<AuthorDto> authors;
    private AssessmentDto assessment;
}
