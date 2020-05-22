package com.tracked.api.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Value("${tracked.api.firebase.database.url}")
    private String databaseUrl;

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.getApplicationDefault())
                .setDatabaseUrl(databaseUrl).build();
        FirebaseApp.initializeApp(options);

        return FirebaseAuth.getInstance();
    }
}
