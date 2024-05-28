package com.kubixdev.bingr.service;

import com.kubixdev.bingr.entity.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieServiceInterface {
    void saveOrUpdate(Movie movie);
    Optional<Movie> getById(int id);
    List<Movie> getAll();
    void delete(int id);
}