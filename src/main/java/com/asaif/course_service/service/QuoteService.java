package com.asaif.course_service.service;

import com.asaif.course_service.model.Quote;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {
    private final String baseUrl;
    private final RestTemplate restTemplate;
    public QuoteService(@Value("${quotes.api.base-url}") String baseUrl) {
        this.baseUrl = baseUrl;
        this.restTemplate = new RestTemplate();
    }
    public Quote getQuote() {
        Quote[] response = restTemplate.getForObject(baseUrl, Quote[].class);
        return response[0];
    }
}
