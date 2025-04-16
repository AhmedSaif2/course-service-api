package com.asaif.course_service.mapper;

import com.asaif.course_service.dto.AssessmentDto;
import com.asaif.course_service.model.Assessment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AssessmentMapper {
    Assessment dtoToAssessment(AssessmentDto assessment);
    AssessmentDto assessmentToDto(Assessment assessment);
}
