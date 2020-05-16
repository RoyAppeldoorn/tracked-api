package com.tracked.api.service.impl;

import com.tracked.api.config.firebase.FirebaseTokenHolder;
import com.tracked.api.service.FirebaseService;
import com.tracked.api.service.shared.FirebaseParser;
import org.springframework.stereotype.Service;

@Service
public class FirebaseServiceImpl implements FirebaseService {

    @Override
    public FirebaseTokenHolder parseToken(String firebaseToken) {
        return new FirebaseParser().parseToken(firebaseToken);
    }
}