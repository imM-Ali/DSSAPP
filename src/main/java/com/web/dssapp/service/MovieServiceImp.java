package com.web.dssapp.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dssapp.model.Movie;
import com.web.dssapp.repository.MongoRepo;

@Service
public class MovieServiceImp implements MovieService {
	
	@Autowired
	MongoRepo _db;

	@Override
	public Movie saveMovie(Movie movie) {
		return _db.save(movie);
	}

	@Override
	public List<Movie> getAllMovies() {
		List<Movie> allMovies = _db.findAll();
		return allMovies;
	}

	@Override
	public Movie getMovieById(int movieId) {
		Optional<Movie> movie = _db.findById(movieId);
        if (movie.isPresent()) {
            return movie.get();
        }
        return null;
	}

	@Override
	public Movie updateMovieById(int movieId, Movie movie) {
		Optional<Movie> optMovie = _db.findById(movieId);

        if (optMovie.isPresent()) {
            Movie ogMovie = optMovie.get();

            if (Objects.nonNull(movie.getTitleAndGenre()) && !"".equalsIgnoreCase(movie.getTitleAndGenre())) {
            	ogMovie.setTitleAndGenre(movie.getTitleAndGenre());
            }
            return _db.save(ogMovie);
        }
        return null;
	}

	@Override
	public String deleteMovieById(int movieId) {
		if (_db.findById(movieId).isPresent()) {
            _db.deleteById(movieId);
            return "Employee deleted successfully";
        }
        return "No such employee in the database";
	}



}
