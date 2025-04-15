package com.asaif.course_service.controller;


import com.asaif.course_service.model.Author;
import com.asaif.course_service.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping("/{mail}")
    public Author getByMail(@PathVariable String mail){
        return authorService.getAuthorByMail(mail);
    }
    @GetMapping
    public List<Author> getAll(){
        return authorService.getAllAuthors();
    }
}
