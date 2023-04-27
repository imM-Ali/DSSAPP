package com.web.dssapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.web.dssapp.model.Movie;
import com.web.dssapp.repository.MongoRepo;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MongoRepo _db;
	private int maxid;

	@Override
	public Boolean addMovie(Movie movie) {

		try {
			maxId();
			movie.set_id(maxid + 1);
			_db.save(movie);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public Page<Movie> getAllMovies(int pageNumber, int pageSize, Sort sort) {		
		
		Pageable page =  PageRequest.of(pageNumber-1, pageSize, sort);
		
		
		return _db.findAll(page);

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
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public Boolean updateMovie(Movie movie, Movie movieDTO) {
		try {
			movie.setAvgRating(movieDTO.getAvgRating());
			movie.setTitle(movieDTO.getTitle());
			movie.setDirectedBy(movieDTO.getDirectedBy());
			movie.setStarring(movieDTO.getStarring());
			movie.setTxt(movieDTO.getTxt());
			_db.save(movie);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void maxId() {		
		maxid = _db.max();
	}

}
