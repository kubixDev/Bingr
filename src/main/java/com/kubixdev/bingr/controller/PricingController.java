package com.kubixdev.bingr.controller;

import com.kubixdev.bingr.entity.User;
import com.kubixdev.bingr.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PricingController {

    private final UserRepository userRepository;

    @Autowired
    public PricingController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/pricing")
    public String pricing(Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userRepository.findByUsername(currentUsername).orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("username", currentUsername);
        model.addAttribute("subscriptionPlan", user.getSubscriptionPlan());
        model.addAttribute("currentPath", request.getRequestURI());

        if ("none".equals(user.getSubscriptionPlan()) || user.getSubscriptionDate() == null) {
            model.addAttribute("daysLeft", 0);
            model.addAttribute("isSubscribed", false);
            model.addAttribute("subscriptionStartDate", null);
        }
        else {
            model.addAttribute("daysLeft", user.getDaysLeftInSubscription(userRepository));
            model.addAttribute("isSubscribed", true);
            model.addAttribute("subscriptionStartDate", user.getSubscriptionDate());
        }

        return "pricing";
    }
}