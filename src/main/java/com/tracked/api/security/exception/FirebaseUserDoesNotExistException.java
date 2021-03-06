package com.tracked.api.security.exception;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

public class FirebaseUserDoesNotExistException extends AuthenticationCredentialsNotFoundException {
    public FirebaseUserDoesNotExistException() {
        super("Firebase user not found");
    }
}
