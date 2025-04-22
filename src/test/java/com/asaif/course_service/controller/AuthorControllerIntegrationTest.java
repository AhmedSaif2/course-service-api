package com.asaif.course_service.controller;

import com.asaif.course_service.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class AuthorControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthorRepository authorRepository;

    private int initialAuthorsCount;
    @BeforeEach
    void setUp() {
        initialAuthorsCount = authorRepository.findAll().size();
    }
    @Test
    void getAllAuthors_authorsExist_returnsAuthors() throws Exception {
        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(initialAuthorsCount)));
    }
    @Test
    void getAllAuthors_noAuthors_returnsEmptyList() throws Exception {
        authorRepository.deleteAll();
        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }
    @Test
    void getAuthorByMail_authorExists_returnsAuthor() throws Exception {
        String authorMail = authorRepository.findAll().get(0).getMail();
        mockMvc.perform(get("/authors/" + authorMail))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mail").value(authorMail));
    }
    @Test
    void getAuthorByMail_invalidMail_returnsNotFound() throws Exception {
        mockMvc.perform(get("/authors/" + "not-found@mail.com"))
                .andExpect(status().isNotFound());
    }

}
