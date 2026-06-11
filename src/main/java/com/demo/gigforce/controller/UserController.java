package com.demo.gigforce.controller;

import com.demo.gigforce.dto.request.ApprovalRequest;
import com.demo.gigforce.entity.User;
import com.demo.gigforce.service.AuditLogService;
import com.demo.gigforce.service.UserService;

import com.demo.gigforce.enums.ApprovalStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AuditLogService auditLogService;

    // Constructor Injection
    public UserController(UserService userService, AuditLogService auditLogService) {
        this.userService = userService;
        this.auditLogService = auditLogService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.createUser(user);

        auditLogService.log(created.getUserId(), "CREATE", "USER");

        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        Optional<User> user = userService.getUserById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @RequestBody User user) {

        User updated = userService.updateUser(id, user);

        auditLogService.log(id, "UPDATE", "USER");

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        auditLogService.log(id, "DELETE", "USER");

        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}/approvalstatus")
    public ResponseEntity<User> updateApprovalStatus(
            @PathVariable Long id,
            @RequestBody ApprovalRequest request) {

        User user = userService.updateApprovalStatus(id, request.getStatus());

        auditLogService.log(id, request.getStatus().name(), "USER");

        return ResponseEntity.ok(user);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<User>> getPendingUsers() {
        return ResponseEntity.ok(userService.getPendingUsers());
    }

}