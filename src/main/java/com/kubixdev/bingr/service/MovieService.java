package com.kubixdev.bingr.service;

import com.kubixdev.bingr.entity.Movie;
import com.kubixdev.bingr.repository.MovieRepositoryInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements MovieServiceInterface {

    private final MovieRepositoryInterface movieRepositoryInterface;

    @Autowired
    public MovieService(MovieRepositoryInterface movieRepositoryInterface) {
        this.movieRepositoryInterface = movieRepositoryInterface;
    }

    @Override
    @Transactional
    public Optional<Movie> getById(int id) {
        return this.movieRepositoryInterface.getById(id);
    }

    @Override
    @Transactional
    public List<Movie> getAll() {
        return this.movieRepositoryInterface.getAll();
    }

    @Override
    @Transactional
    public void saveOrUpdate(Movie movie) {
        this.movieRepositoryInterface.saveOrUpdate(movie);
    }

    @Override
    @Transactional
    public void delete(int id) {
        this.movieRepositoryInterface.delete(id);
    }
}