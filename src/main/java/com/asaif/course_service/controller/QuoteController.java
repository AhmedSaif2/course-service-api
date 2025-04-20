package com.asaif.course_service.controller;

import com.asaif.course_service.model.Quote;
import com.asaif.course_service.service.QuoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

    private final QuoteService quoteService;
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }
    @GetMapping("/quote")
    public Quote getQuote() {
        return quoteService.getQuote();
    }
}
