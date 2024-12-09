package com.farmpulse.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.farmpulse.backend.dto.ReqRes;
import com.farmpulse.backend.entity.OurUsers;
import com.farmpulse.backend.service.UsersManagementService;

@RestController
@RequestMapping("/api")
public class UserManagementController {

    @Autowired
    private UsersManagementService usersManagementService;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg) {
        return ResponseEntity.ok(usersManagementService.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req) {
        System.out.println(req.getEmail());
        return ResponseEntity.ok(usersManagementService.login(req));

    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req) {
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers() {
        return ResponseEntity.ok(usersManagementService.getAllUsers());
    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<ReqRes> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.ok(usersManagementService.getUsersById(userId));
    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody OurUsers reqres) {
        return ResponseEntity.ok(usersManagementService.updateUser(userId, reqres));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = usersManagementService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(usersManagementService.deleteUser(userId));
    }

    @GetMapping("/admin/manage-experts")
    public ResponseEntity<?> manageExperts() {
        // Administrator-specific logic
        return ResponseEntity.ok("Manage Experts");
    }

    @GetMapping("/farmer/request-advice")
    public ResponseEntity<?> requestAdvice() {
        // Farmer-specific logic
        return ResponseEntity.ok("Request Advice");
    }

    @GetMapping("/expert/analyze-crop")
    public ResponseEntity<?> analyzeCrop() {
        // Expert-specific logic
        return ResponseEntity.ok("Analyze Crop");
    }

    @GetMapping("/public/resources")
    public ResponseEntity<?> getResources() {
        // Public User-specific logic
        return ResponseEntity.ok("Access Educational Resources");
    }
}
