package com.dynatrace.bidding.controller;

import com.dynatrace.bidding.model.User;
import com.dynatrace.bidding.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userService.registerUser(user);

        // --- Add this logging line ---
        logger.info("BIZ_METRIC: User registered successfully. | userId={} | username={}", newUser.getId(), newUser.getUsername());

        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        if (userService.validateUser(user.getUsername(), user.getPassword())) {
            // In a real app, you would generate and return a token here
            // --- Add this logging line ---
            logger.info("BIZ_METRIC: User logged in successfully. | username={}", user.getUsername());

            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
