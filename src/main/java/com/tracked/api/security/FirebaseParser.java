package com.tracked.api.security;

import com.google.api.core.ApiFuture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.tracked.api.security.exception.InvalidFirebaseTokenException;
import com.tracked.api.security.model.FirebaseUserDetails;
import com.tracked.api.security.service.FirebaseService;
import org.springframework.stereotype.Service;

@Service
public class FirebaseParser implements FirebaseService {
    public FirebaseUserDetails parseToken(String firebaseToken) {
        try {
            ApiFuture<FirebaseToken> task = FirebaseAuth.getInstance().verifyIdTokenAsync(firebaseToken);
            FirebaseToken token = task.get();

            return new FirebaseUserDetails(token);
        } catch (Exception e) {
            throw new InvalidFirebaseTokenException();
        }
    }
}
