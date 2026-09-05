package com.proyecto_final.Pizza4You;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

import java.io.FileInputStream;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

	@PostConstruct
	public void inicializar() {
	    try {
	        String saPath = System.getenv("FIREBASE_SA_PATH");
	        InputStream serviceAccount;
	        
	        if (saPath != null && !saPath.isEmpty()) {
	            serviceAccount = new FileInputStream(saPath);
	        } else {
	            serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase-service-account.json");
	        }
	        
	        if (serviceAccount != null) {
	            FirebaseOptions options = FirebaseOptions.builder()
	                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
	                .build();
	            if (FirebaseApp.getApps().isEmpty()) {
	                FirebaseApp.initializeApp(options);
	            }
	        }
	    } catch (Exception e) {
	        // firebase es opcional
	    }
	}
}
