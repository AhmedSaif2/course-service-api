package com.asaif.course_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "coursesClient", url = "http://localhost:8081/api")
public interface CoursesClient {
    @GetMapping(value = "/recommended-courses",consumes = "application/xml")
    String getCourses();
}
