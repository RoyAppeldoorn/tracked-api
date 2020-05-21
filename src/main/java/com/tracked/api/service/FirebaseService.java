package com.tracked.api.service;

import com.tracked.api.config.firebase.model.FirebaseUserDetails;

public interface FirebaseService {
    FirebaseUserDetails parseToken(String firebaseToken);
}
