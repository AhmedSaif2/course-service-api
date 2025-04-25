package com.asaif.course_service.service;

import com.asaif.course_service.dto.AuthorDto;
import com.asaif.course_service.mapper.AuthorMapper;
import com.asaif.course_service.model.Author;
import com.asaif.course_service.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {
    @Mock
    AuthorRepository authorRepository;
    @Mock
    AuthorMapper authorMapper;
    @InjectMocks
    AuthorService authorService;
    private Author testAuthor;
    private AuthorDto testAuthorDto;

    @BeforeEach
    void setUp() {
        testAuthor = new Author();
        testAuthor.setId(1L);
        testAuthor.setName("Test Author");
        testAuthor.setMail("test@test.com");

        testAuthorDto = new AuthorDto();
        testAuthorDto.setName("Test Author");
        testAuthorDto.setMail("test@test.com");

    }
    @Test
    void getAllAuthors_authorsExist_returnsAllAuthors() {
        when(authorRepository.findAll()).thenReturn(List.of(testAuthor));
        when(authorMapper.authorsToDtos(List.of(testAuthor))).thenReturn(List.of(testAuthorDto));

        List<AuthorDto> result = authorService.getAllAuthors();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testAuthorDto.getName(), result.get(0).getName());
        assertEquals(testAuthorDto.getMail(), result.get(0).getMail());
    }
    @Test
    void getAuthorByMail_existingMail_returnsCorrectAuthor() {
        when(authorRepository.findByMail(testAuthor.getMail())).thenReturn(testAuthor);
        when(authorMapper.authorToDto(testAuthor)).thenReturn(testAuthorDto);

        AuthorDto result = authorService.getAuthorByMail(testAuthor.getMail());

        assertNotNull(result);
        assertEquals(testAuthorDto.getName(), result.getName());
        assertEquals(testAuthorDto.getMail(), result.getMail());
    }
    @Test
    void getAuthorByMail_nonExistingMail_returnsNull() {
        when(authorRepository.findByMail("nonExistingMail")).thenReturn(null);

        AuthorDto result = authorService.getAuthorByMail("nonExistingMail");

        assertNull(result);
    }
}
