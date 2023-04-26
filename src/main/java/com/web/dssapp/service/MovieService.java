package com.web.dssapp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.web.dssapp.model.Movie;

@Service
public interface MovieService {

	public Movie addMovie(Movie movie);
	public List<Movie> getAllMovies();
	public Optional<Movie> getMovieById(ObjectId id);
	public String deleteMovieById(ObjectId id);
	
}
