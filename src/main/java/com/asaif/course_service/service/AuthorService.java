package com.asaif.course_service.service;

import com.asaif.course_service.dto.AuthorDto;
import com.asaif.course_service.mapper.AuthorMapper;
import com.asaif.course_service.model.Author;
import com.asaif.course_service.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }
    public List<AuthorDto> getAllAuthors(){
        return authorMapper.authorsToDtos(authorRepository.findAll());
    }
    public AuthorDto getAuthorByMail(String name){
        Author author = authorRepository.findByMail(name);
        if (author == null) {
            return null;
        }
        return authorMapper.authorToDto(author);
    }
}
