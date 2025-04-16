package com.asaif.course_service.controller;


import com.asaif.course_service.dto.AuthorDto;
import com.asaif.course_service.mapper.AuthorMapper;
import com.asaif.course_service.model.Author;
import com.asaif.course_service.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;
    public AuthorController(AuthorService authorService,AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }
    @GetMapping("/{mail}")
    public ResponseEntity<AuthorDto> getByMail(@PathVariable String mail){
        Author author = authorService.getAuthorByMail(mail);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authorMapper.authorToDto(author));
    }
    @GetMapping
    public List<AuthorDto> getAll(){
        return authorMapper.authorsToDtos(authorService.getAllAuthors());
    }
}
