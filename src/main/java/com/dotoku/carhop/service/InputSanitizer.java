package com.dotoku.carhop.service;

import org.springframework.stereotype.Service;

@Service
public class InputSanitizer {
    public String sanitize(String input) {
        return input.replaceAll("[<>\"'%;()&+]", ""); // Basic XSS/SQL injection prevention
    }
}
