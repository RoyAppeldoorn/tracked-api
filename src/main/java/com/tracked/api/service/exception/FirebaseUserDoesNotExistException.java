package com.tracked.api.service.exception;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

public class FirebaseUserDoesNotExistException extends AuthenticationCredentialsNotFoundException {

    public FirebaseUserDoesNotExistException() {
        super("User not found");
    }

}
