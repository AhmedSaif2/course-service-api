package com.asaif.course_service.service;

import com.asaif.course_service.model.Quote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(WireMockExtension.class)
@AutoConfigureMockMvc
class QuoteServiceTest {
    @Autowired
    private QuoteService quoteService;
    @Autowired
    private ObjectMapper mapper;

    @RegisterExtension
    static WireMockExtension wireMockExtension = WireMockExtension.newInstance()
            .options(WireMockConfiguration.wireMockConfig().dynamicPort())
            .build();
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        String fullPath = wireMockExtension.baseUrl() + "/api/random";
        registry.add("quotes.api.base-url", () -> fullPath);
    }
    @Test
    void testGetQuote() throws JsonProcessingException {
        Quote expectedQuoteObject = new Quote();
        expectedQuoteObject.setQ("Test Quote");
        expectedQuoteObject.setA("Test Author");

        wireMockExtension.stubFor(WireMock.get(WireMock.urlEqualTo("/api/random"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(mapper.writeValueAsString(List.of(expectedQuoteObject)))));

        Quote quote = quoteService.getQuote();

        assertEquals(expectedQuoteObject.getQ(), quote.getQ());
        assertEquals(expectedQuoteObject.getA(), quote.getA());
    }
}