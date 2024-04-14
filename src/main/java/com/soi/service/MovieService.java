package com.soi.service;

import com.soi.model.Movie;
import com.soi.model.MoviesWrapper;
import com.soi.repository.MovieRepository;
import java.util.List;

public class MovieService {

    private final MovieRepository movieRepository = new MovieRepository();

    public MoviesWrapper getAllMovies() {
        return movieRepository.findAllMovies();
    }

    public Movie getMovieById(int id) {
        return movieRepository.findMovieById(id);
    }

    public List<Integer> getMoviesByYearSorted(int year, String orderBy) {
        return movieRepository.findMovieByYear(year, orderBy);
    }

    public int createMovie(Movie movie) {
        return movieRepository.addMovie(movie);
    }

    public void updateMovie(Movie movie, int id) {
        movieRepository.updateMovie(movie, id);
    }

    public void deleteMovie(int id) {
        movieRepository.deleteMovie(id);
    }
}
