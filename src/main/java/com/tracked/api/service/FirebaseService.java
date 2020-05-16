package com.tracked.api.service;

import com.tracked.api.config.firebase.FirebaseTokenHolder;

public interface FirebaseService {

    FirebaseTokenHolder parseToken(String idToken);

}
