package com.shop.rainboweasywalk.controller;

import com.shop.rainboweasywalk.model.UserLogin;
import com.shop.rainboweasywalk.repo.UserLoginRepository;
import com.shop.rainboweasywalk.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Optional<UserLogin> user = authService.login(username, password);
        if (user.isPresent()) {
            session.setAttribute("loggedInUser", user.get());
            return ResponseEntity.ok("Login successful as " + user.get().getRole());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/login-required")
    public ResponseEntity<String> loginRequired() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login to access this page.");
    }

    @PostMapping("/admin/change-password")
    public ResponseEntity<String> changePassword(HttpSession session,
                                                 @RequestParam String targetUsername,
                                                 @RequestParam String newPassword) {
        UserLogin currentUser = (UserLogin) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login first.");
        }

        if (!"ADMIN".equalsIgnoreCase(currentUser.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admins can change passwords.");
        }

        Optional<UserLogin> targetUser = userLoginRepository.findByUsername(targetUsername);
        if (targetUser.isPresent()) {
            UserLogin user = targetUser.get();
            user.setPassword(newPassword);
            userLoginRepository.save(user);
            return ResponseEntity.ok("Password changed successfully.");
        } else {
            return ResponseEntity.badRequest().body("User not found.");
        }
    }
}
