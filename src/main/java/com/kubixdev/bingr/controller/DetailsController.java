package com.kubixdev.bingr.controller;

import com.kubixdev.bingr.entity.Movie;
import com.kubixdev.bingr.entity.User;
import com.kubixdev.bingr.repository.MovieRepository;
import com.kubixdev.bingr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class DetailsController {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public DetailsController(UserRepository userRepository, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movie/{id}")
    public String movieDetails(@PathVariable int id, Model model) {
        Movie movie = movieRepository.getById(id).orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        model.addAttribute("movie", movie);
        return "movie-details";
    }

    @PostMapping("/movie/{id}/add-to-favorites")
    public String addToFavorites(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Movie movie = movieRepository.getById(id).orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        List<Movie> favorites = user.getFavoriteMovies();
        if (!favorites.contains(movie)) {
            favorites.add(movie);
            user.setFavoriteMovies(favorites);
            userRepository.save(user);
        }

        return "redirect:/movie/" + id;
    }
}