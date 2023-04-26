package com.web.dssapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import com.web.dssapp.model.Movie;
import com.web.dssapp.repository.MongoRepo;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	MongoRepo _db;
	private int maxid=0;
	
	@Override
	public Boolean addMovie(Movie movie) {	
		
		try {
			movie.set_id(maxid+1);
			_db.save(movie);
			return true;
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;			
		}		
	}

	@Override
	public List<Movie> getAllMovies() {
		List<Movie> allMovies = _db.findAll(Sort.by(Sort.Direction.ASC, "_id"));	
		maxid = allMovies.get(allMovies.size()-1).get_id();
		return allMovies;
		
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
