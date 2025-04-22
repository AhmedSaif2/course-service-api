package com.asaif.course_service.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class ValidationReportFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String hasValidation = request.getHeader("X-Validation-Report");
        if (hasValidation!=null && hasValidation.equals("true")) {
            filterChain.doFilter(request, response);
            return;
        }
        throw new AccessDeniedException("Access Denied");

    }
}
