package com.syncspace.backend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    // You can optionally set the path in application.properties, otherwise it defaults to resources/firebase
    @Value("${firebase.credentials.file:firebase/firebase-service-account.json}")
    private String credentialsFile;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        // Avoid re-initialization if FirebaseApp already exists
        if (!FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.getInstance();
        }

        FirebaseOptions.Builder builder = FirebaseOptions.builder();

        InputStream serviceAccount = null;

        // Try loading from resources first
        serviceAccount = getClass().getClassLoader().getResourceAsStream(credentialsFile);

        // If not found in resources, try loading as absolute path
        if (serviceAccount == null) {
            serviceAccount = new FileInputStream(credentialsFile);
        }

        builder.setCredentials(GoogleCredentials.fromStream(serviceAccount));

        FirebaseOptions options = builder.build();
        return FirebaseApp.initializeApp(options);
    }
}




// package com.syncspace.backend.config;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import java.io.FileInputStream;
// import java.io.IOException;

// @Configuration
// public class FirebaseConfig {

//     @Value("${firebase.credentials.file:}")
//     private String credentialsFile;

//     @Bean
//     public FirebaseApp firebaseApp() throws IOException {
//         if (!FirebaseApp.getApps().isEmpty()) {
//             return FirebaseApp.getInstance();
//         }

//         FirebaseOptions.Builder builder = FirebaseOptions.builder();
//         if (credentialsFile != null && !credentialsFile.isBlank()) {
//             try (FileInputStream serviceAccount = new FileInputStream(credentialsFile)) {
//                 builder.setCredentials(GoogleCredentials.fromStream(serviceAccount));
//             }
//         } else {
//             builder.setCredentials(GoogleCredentials.getApplicationDefault());
//         }
//         FirebaseOptions options = builder.build();
//         return FirebaseApp.initializeApp(options);
//     }
// }
