package com.web.dssapp.controller;

import com.web.dssapp.model.Movie;
import com.web.dssapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/movie")
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @GetMapping("/movie")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movie/{id}")
    public Movie getMovieById(@PathVariable("id") int id) {
        return movieService.getMovieById(id);
    }

    @PutMapping("/movie/{id}")
    public Movie updateMovie(@PathVariable("id") int id, @RequestBody Movie movie) {
        return movieService.updateMovieById(id, movie);
    }

    @DeleteMapping("/movie/{id}")
    public String deleteMovie(@PathVariable("id") int id) {
        return movieService.deleteMovieById(id);
    }
}

