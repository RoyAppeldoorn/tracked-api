package com.tracked.api.service.shared;

import com.google.api.core.ApiFuture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.tracked.api.config.firebase.exception.InvalidFirebaseTokenException;
import com.tracked.api.config.firebase.model.FirebaseUserDetails;
import com.tracked.api.service.FirebaseService;
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
