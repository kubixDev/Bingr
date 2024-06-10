package com.kubixdev.bingr.controller;

import com.kubixdev.bingr.entity.User;
import com.kubixdev.bingr.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;

@Controller
public class SubscriptionController {

    private final UserRepository userRepository;

    public SubscriptionController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/subscribe")
    public String subscribe(@RequestParam("plan") String plan) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userRepository.findByUsername(currentUsername).orElseThrow(() -> new RuntimeException("User not found"));

        user.setSubscriptionPlan(plan);
        user.setSubscriptionDate(LocalDate.now());
        userRepository.save(user);

        return "redirect:/pricing";
    }
}