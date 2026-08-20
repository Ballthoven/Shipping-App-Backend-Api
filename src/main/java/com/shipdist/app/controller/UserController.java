package com.shipdist.app.controller;
import com.shipdist.app.dto.UserResponse;
import com.shipdist.app.entity.Role;
import com.shipdist.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll().stream().map(UserResponse::from).collect(Collectors.toList());
    }

    @PatchMapping("/{id}/role")
    public UserResponse updateRole(@PathVariable UUID id, @RequestBody RoleUpdateRequest request) {
        return UserResponse.from(userService.updateRole(id, request.role()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    public record RoleUpdateRequest(Role role) {

    }
}
