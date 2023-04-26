package com.web.dssapp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dssapp.model.Movie;
import com.web.dssapp.repository.MongoRepo;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	MongoRepo _db;
	
	
	@Override
	public Movie addMovie(Movie movie) {
		_db.save(movie);
		return movie;
	}

	@Override
	public List<Movie> getAllMovies() {
		return _db.findAll();
	}

	@Override
	public Optional<Movie> getMovieById(ObjectId id) {
		return _db.findById(id);
	}

	@Override
	public String deleteMovieById(ObjectId id) {
		try {
			_db.deleteById(id);
			return "Movie deleted successfully";
		}catch(Exception e) {
			return e.getMessage();
		}
	}

}
