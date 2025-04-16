package com.asaif.course_service.mapper;

import com.asaif.course_service.dto.CourseDto;
import com.asaif.course_service.model.Course;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDto courseToDto(Course course);
    Course dtoToCourse(CourseDto courseDto);
    List<CourseDto> coursesToDtos(List<Course> courses);
    List<Course> dtosToCourses(List<CourseDto> courseDtos);
}
