package com.tracked.api.service.shared;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.tracked.api.config.firebase.FirebaseTokenHolder;
import com.tracked.api.service.exception.FirebaseTokenInvalidException;

public class FirebaseParser {
    public FirebaseTokenHolder parseToken(String idToken) {
        if (idToken.isBlank()) {
            throw new IllegalArgumentException("FirebaseTokenBlank");
        }
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            return new FirebaseTokenHolder(decodedToken);
        } catch (Exception e) {
            throw new FirebaseTokenInvalidException(e.getMessage());
        }
    }
}
