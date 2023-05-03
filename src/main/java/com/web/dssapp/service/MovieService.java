package com.web.dssapp.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.web.dssapp.model.Movie;

@Service
public interface MovieService {

	public Boolean addMovie(Movie movie);
	public Page<Movie> getAllMovies(int pageNumber, int pageSize, Sort sort);
	public Optional<Movie> getMovieById(int id);
	public String deleteMovieById(int id);
	public Boolean updateMovie(Movie movie, Movie movieDTO);
	public void maxId();
	
}
