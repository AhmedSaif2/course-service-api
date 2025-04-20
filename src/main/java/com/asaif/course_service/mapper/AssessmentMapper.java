package com.asaif.course_service.mapper;

import com.asaif.course_service.dto.AssessmentDto;
import com.asaif.course_service.model.Assessment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssessmentMapper {
    AssessmentDto assessmentToDto(Assessment assessment);
}
