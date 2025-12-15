package com.syncspace.backend.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody Map<String, String> body) {
        String idToken = body.get("idToken");
        if (idToken == null || idToken.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "idToken is required"));
        }
        try {
            FirebaseToken decoded = FirebaseAuth.getInstance().verifyIdToken(idToken);
            return ResponseEntity.ok(Map.of(
                    "uid", decoded.getUid(),
                    "email", decoded.getEmail(),
                    "name", decoded.getName()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "invalid token"));
        }
    }
}



