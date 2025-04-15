package com.asaif.course_service.repository;

import com.asaif.course_service.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {
    Author findByMail(String name);
}
