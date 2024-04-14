package com.soi.repository;

import com.soi.controller.MovieController;
import com.soi.model.Movie;
import com.soi.model.MoviesWrapper;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MovieRepository {

    private static final Map<Integer, Movie> movies = new HashMap<>();
    private static final AtomicInteger idCounter = new AtomicInteger();
    private static final Logger LOGGER = Logger.getLogger(MovieController.class.getName());

    public MoviesWrapper findAllMovies() {
        LOGGER.log(Level.INFO,"Movies available in Database {}", movies);
        return new MoviesWrapper(new ArrayList<>(movies.values()));
    }

    public Movie findMovieById(int id) {
        return movies.get(id);
    }

    public List<Integer> findMovieByYear(int year, String orderBy) {
        List<Integer> filteredMovies = movies.entrySet().stream()
                .filter(entry -> entry.getValue().getYear() == year)
                .sorted((entry1, entry2) -> {
                    Movie m1 = entry1.getValue();
                    Movie m2 = entry2.getValue();
                    if ("title".equalsIgnoreCase(orderBy)) {
                        return m1.getTitle().compareToIgnoreCase(m2.getTitle());
                    } else if ("director".equalsIgnoreCase(orderBy)) {
                        return m1.getDirector().compareToIgnoreCase(m2.getDirector());
                    }
                    return 0;
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        LOGGER.log(Level.INFO, "Movies fetched from year " + year + " sorted by " + orderBy + ": " + filteredMovies);
        return filteredMovies;
    }

    public int addMovie(Movie movie) {
        int id = idCounter.incrementAndGet();
        movies.put(id, movie);
        LOGGER.log(Level.INFO, "Movie with ID: {} is added to the list", id);
        LOGGER.log(Level.INFO,"List of available movies after addition: {}", movies);
        return id;
    }

    public void updateMovie(Movie movie, int id) {
        if (movies.containsKey(id)) {
            movies.put(id, movie);
            LOGGER.log(Level.INFO, "Movie with ID " + id + " updated: " + movie);
        } else {
            LOGGER.log(Level.WARNING, "Movie with ID " + id + " not found for update. Creating a new movie entry");
            id = idCounter.incrementAndGet();
            movies.put(id, movie);
            LOGGER.log(Level.INFO, "Movie with ID " + id + " is added to the list " + movie);
        }
    }

    public void deleteMovie(int id) {
        if (movies.containsKey(id)) {
            movies.remove(id);
            LOGGER.log(Level.INFO, "Movie with ID " + id + " deleted.");
        } else {
            LOGGER.log(Level.WARNING, "Movie with ID " + id + " not found for deletion.");
        }
    }
}
