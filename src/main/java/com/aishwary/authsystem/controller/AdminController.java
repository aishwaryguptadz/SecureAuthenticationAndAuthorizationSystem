package com.aishwary.authsystem.controller;

import com.aishwary.authsystem.model.Role;
import com.aishwary.authsystem.model.User;
import com.aishwary.authsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final UserRepository userRepo;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
        return "User has been deleted";
    }

    @PutMapping("/promote/{id}")
    public String promoteUser(@PathVariable Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(Role.ADMIN);
        userRepo.save(user);
        return "User promoted to ADMIN";
    }

    @PutMapping("/demote/{id}")
    public String demoteUser(@PathVariable Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(Role.USER);
        userRepo.save(user);
        return "User demoted to USER";
    }
}
