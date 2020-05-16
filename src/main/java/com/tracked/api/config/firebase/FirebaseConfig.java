package com.tracked.api.config.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public DatabaseReference firebaseDatabase() {
        return FirebaseDatabase.getInstance().getReference();
    }

    @Value("${tracked.api.firebase.database.url}")
    private String databaseUrl;

    @PostConstruct
    public void init() throws IOException {
        // Environment variable set for Admin SDK config path, for security reasons
        // https://firebase.google.com/docs/admin/setup#windows
        FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.getApplicationDefault())
                .setDatabaseUrl(databaseUrl).build();
        FirebaseApp.initializeApp(options);
    }
}
