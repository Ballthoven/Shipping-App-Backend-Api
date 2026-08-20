package com.shipdist.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/")
    public ResponseEntity<String> root() {
        return ResponseEntity.ok("The backend is alive and working sigh");
    }
}
