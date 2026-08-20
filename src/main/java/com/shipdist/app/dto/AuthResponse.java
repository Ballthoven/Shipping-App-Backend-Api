package com.shipdist.app.dto;

import java.util.UUID;

public class AuthResponse {
    private String token;
    private UUID userId;
    private String fullName;
    private String email;

    public AuthResponse(String token, UUID userId, String fullName, String email) {
        this.token = token;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
