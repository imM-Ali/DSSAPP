package com.web.dssapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.web.dssapp.model.Movie;
import com.web.dssapp.repository.MongoRepo;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	MongoRepo _db;
	
	
	@Override
	public Boolean addMovie(Movie movie) {
		Optional<Movie> found= _db.findById(movie.get_id());
		if( found != null) {
			return false;
		}else {
			_db.save(movie);			
			return true;
		}
		
	}

	@Override
	public List<Movie> getAllMovies() {
		return _db.findAll(Sort.by(Sort.Direction.ASC, "_id"));
		
	}

	@Override
	public Optional<Movie> getMovieById(int id) {
		return _db.findById(id);
	}

	@Override
	public String deleteMovieById(int id) {
		try {
			_db.deleteById(id);
			return "Movie deleted successfully";
		}catch(Exception e) {
			return e.getMessage();
		}
	}

	/*
	 * @Override public Boolean updateMovie(Movie movie) { try { _db.save(movie);
	 * return true; }catch(Exception e) { return false; }
	 * 
	 * 
	 * }
	 */

}
