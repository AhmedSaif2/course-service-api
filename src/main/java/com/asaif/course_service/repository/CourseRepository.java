package com.asaif.course_service.repository;

import com.asaif.course_service.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, String> {

}
