package com.kubixdev.bingr.repository;

import com.kubixdev.bingr.entity.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieRepositoryInterface {
    void saveOrUpdate(Movie movie);
    Optional<Movie> getById(int id);
    List<Movie> getAll();
    void delete(int id);
}
