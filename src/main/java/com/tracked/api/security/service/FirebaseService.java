package com.tracked.api.security.service;

import com.tracked.api.security.model.FirebaseUserDetails;

public interface FirebaseService {
    FirebaseUserDetails parseToken(String firebaseToken);
}
