package com.tracked.api.security.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class InvalidFirebaseTokenException extends BadCredentialsException {
    public InvalidFirebaseTokenException() {
        super("Invalid firebase token");
    }
}
