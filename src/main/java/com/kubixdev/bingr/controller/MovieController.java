package com.kubixdev.bingr.controller;

import com.kubixdev.bingr.entity.Movie;
import com.kubixdev.bingr.service.MovieServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/movies")
public class MovieController {

    private final MovieServiceInterface movieService;

    @Autowired
    public MovieController(MovieServiceInterface movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String getAllMovies(Model model) {
        List<Movie> movies = movieService.getAll();
        model.addAttribute("movies", movies);
        return "manage-movies";
    }

    @GetMapping("/add")
    public String showAddMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "add-movie";
    }

    @PostMapping("/add")
    public String addMovie(@ModelAttribute Movie movie) {
        movieService.saveOrUpdate(movie);
        return "redirect:/admin/movies";
    }

    @GetMapping("/edit/{id}")
    public String showEditMovieForm(@PathVariable int id, Model model) {
        Optional<Movie> movie = movieService.getById(id);
        if (movie.isPresent()) {
            model.addAttribute("movie", movie.get());
            return "edit-movie";
        } else {
            return "redirect:/admin/movies";
        }
    }

    @PostMapping("/edit/{id}")
    public String editMovie(@PathVariable int id, @ModelAttribute Movie movie) {
        movie.setId(id);
        movieService.saveOrUpdate(movie);
        return "redirect:/admin/movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable int id) {
        movieService.delete(id);
        return "redirect:/admin/movies";
    }
}