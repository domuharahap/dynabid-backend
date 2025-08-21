package com.dynatrace.bidding.services;

import com.dynatrace.bidding.model.User;
import com.dynatrace.bidding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        // In a real application, you'd hash the password here.
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean validateUser(String username, String password) {
        // Find the user by username
        User user = userRepository.findByUsername(username);

        // Check if the user exists and the password matches
        return user != null && user.getPassword().equals(password);
    }
}
