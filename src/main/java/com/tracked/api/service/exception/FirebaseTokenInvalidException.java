package com.tracked.api.service.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class FirebaseTokenInvalidException extends BadCredentialsException {

    public FirebaseTokenInvalidException(String msg) {
        super(msg);
    }

}
