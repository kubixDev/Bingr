package com.kubixdev.bingr.controller;

import com.kubixdev.bingr.entity.Movie;
import com.kubixdev.bingr.entity.User;
import com.kubixdev.bingr.repository.MovieRepository;
import com.kubixdev.bingr.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public HomeController(UserRepository userRepository, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        model.addAttribute("username", user.getUsername());

        List<Movie> movies = movieRepository.getAll();
        model.addAttribute("movies", movies);

        model.addAttribute("currentPath", request.getRequestURI());

        return "home";
    }
}