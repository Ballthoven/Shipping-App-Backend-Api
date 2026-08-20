package com.shipdist.app.dto;

import java.util.UUID;

import java.util.UUID;

public class AuthResponse {
    private String token;
    private UUID userId;
    private String fullName;
    private String email;
    private String role;

    public AuthResponse(String token, UUID userId, String fullName, String email, String role) {
        this.token = token;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }

    public String getToken() { return token; }
    public UUID getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}
