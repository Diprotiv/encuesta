package com.diprotiv.encuesta.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;

@Service
@Slf4j
public class FBInitializeService {

    @PostConstruct
    public void initialize() {
        try {
            log.info("Current path: {}", Paths.get(".").toAbsolutePath());
            InputStream serviceAccount = new FileInputStream("./account.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);
            log.info("FirebaseApp initialized.");
        } catch (Exception e) {
            log.error("Cannot initialize account. {}", e.getMessage());
        }
    }

}
