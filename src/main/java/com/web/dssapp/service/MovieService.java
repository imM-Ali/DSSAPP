package com.web.dssapp.service;

import java.util.List;

import com.web.dssapp.model.Movie;

public interface MovieService {

    Movie saveMovie(Movie movie);

    List<Movie> getAllMovies();

    Movie getMovieById(int movieId);

    Movie updateMovieById(int movieId, Movie movie);

    String deleteMovieById(int movieId);
}
