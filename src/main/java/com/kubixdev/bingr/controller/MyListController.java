package com.kubixdev.bingr.controller;

import com.kubixdev.bingr.entity.Movie;
import com.kubixdev.bingr.entity.User;
import com.kubixdev.bingr.repository.UserRepository;
import com.kubixdev.bingr.service.MovieServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class MyListController {

    private final UserRepository userRepository;

    @Autowired
    public MyListController(MovieServiceInterface movieService, UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/my-list")
    public String myList(Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        model.addAttribute("username", user.getUsername());

        List<Movie> favoriteMovies = user.getFavoriteMovies();
        model.addAttribute("movies", favoriteMovies);

        model.addAttribute("currentPath", request.getRequestURI());

        return "my-list";
    }

    @PostMapping("/delete-movie/{movieId}")
    public String deleteMovieFromUserList(@PathVariable("movieId") int movieId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.getFavoriteMovies().removeIf(movie -> movie.getId() == movieId);
        userRepository.save(user);

        return "redirect:/my-list";
    }
}