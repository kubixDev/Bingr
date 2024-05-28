package com.kubixdev.bingr.repository;

import com.kubixdev.bingr.entity.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository implements MovieRepositoryInterface {

    @PersistenceContext
    private EntityManager entityManager;

    private final String GET_ALL_JPQL = "FROM Movie";

    public MovieRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Movie> getAll() {
        TypedQuery<Movie> query = entityManager.createQuery(GET_ALL_JPQL, Movie.class);
        return query.getResultList();
    }

    @Override
    public void saveOrUpdate(Movie movie) {
        if (movie.getId() == 0) {
            entityManager.persist(movie);
        } else {
            entityManager.merge(movie);
        }
    }

    @Override
    public Optional<Movie> getById(int id) {
        return Optional.ofNullable(entityManager.find(Movie.class, id));
    }

    @Override
    public void delete(int id) {
        Movie movie = entityManager.find(Movie.class, id);
        if (movie != null) {
            entityManager.remove(movie);
        }
    }
}