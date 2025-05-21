package com.epam.training_portal.exceptions;

public class BrowserNotFound extends RuntimeException {
    public BrowserNotFound(String message) {
        super(message);
    }
}
