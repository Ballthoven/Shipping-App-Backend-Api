package com.shipdist.app.dto;

import com.shipdist.app.entity.User;

import java.util.UUID;

public class UserResponse {
    private UUID id;
    private String fullName;
    private String email;
    private String phone;
    private String role;

    public static UserResponse from(User u) {
        UserResponse r = new UserResponse();
        r.id = u.getId();
        r.fullName = u.getFullName();
        r.email = u.getEmail();
        r.phone = u.getPhone();
        r.role = u.getRole().name();
        return r;
    }

    public UUID getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getRole() { return role; }

}
