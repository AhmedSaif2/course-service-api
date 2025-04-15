package com.asaif.course_service.service;

import com.asaif.course_service.model.Author;
import com.asaif.course_service.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }
    public Author getAuthorByMail(String name){
        return authorRepository.findByMail(name);
    }
}
