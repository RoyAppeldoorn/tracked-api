package com.tracked.api.service.exception;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

public class FirebaseUserDoesNotExistException extends AuthenticationCredentialsNotFoundException {

    public FirebaseUserDoesNotExistException() {
        super("User not found");
    }

    private static final long serialVersionUID = 789949671713648425L;
}
