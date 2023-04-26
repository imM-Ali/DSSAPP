package com.web.dssapp.controller;

import com.web.dssapp.model.Movie;
import com.web.dssapp.service.MovieService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class MovieController {

	@Autowired
	private MovieService engine;

	@GetMapping("/movies")
	public String home(Model model) {
		model.addAttribute("movies", engine.getAllMovies());
		return "moviespage";
	}

	@GetMapping("/addMovie")
	public String saveMovie(Model model) {
		model.addAttribute("movie", new Movie());
		return "addmoviepage";
	}

	@PostMapping("/addMovie")
	public String saveMovie(Movie movie, RedirectAttributes redirAttrs) {
		Optional<Movie> found= engine.getMovieById(movie.getId());
		if( found != null) {			
			redirAttrs.addFlashAttribute("status", "Movie ID already exists");
			return "redirect:/addMovie";
		}else {
			engine.addMovie(movie);
			redirAttrs.addFlashAttribute("status", "Movie ID: "+movie.getId()+"saved successfully!");
			return "redirect:/addMovie";
		}
		
	}

	@GetMapping("/editmovie/{id}")
	public String editMovie(@PathVariable("id") ObjectId id, Model model, RedirectAttributes redirAttrs) {		
		Optional<Movie> oldMov = engine.getMovieById(id);
		if (oldMov != null) {
			model.addAttribute("movie", oldMov.get());
			return "editmoviepage";
		} else {
			redirAttrs.addFlashAttribute("status", "Movie ID does not exists");
			return "redirect:/editmovie";
		}		
	}
	
	@PostMapping("/editMovie/{id}")
	public String saveEditedMovie(@PathVariable("id") ObjectId id, Movie editedMovie, RedirectAttributes redirAttrs) {
	    Optional<Movie> oldMov = engine.getMovieById(id);
	    if (oldMov != null) {
	        Movie existingMovie = oldMov.get();
	        existingMovie.setTitle(editedMovie.getTitle());
	        existingMovie.setId(editedMovie.getId());
	        engine.addMovie(existingMovie);
	        redirAttrs.addFlashAttribute("status", "Movie ID: " + id + " saved successfully!");
	        return "redirect:/editMovie/" + id;
	    } else {
	    	redirAttrs.addFlashAttribute("status", "Movie ID does not exists");
			return "redirect:/editmovie";
	    }
	}
	
	@GetMapping("/movies/{id}")
	public Optional<Movie> getMovieById(@PathVariable("id") ObjectId id) {
		return engine.getMovieById(id);
	}

	@DeleteMapping("/movie/{id}")
	public Optional<Movie> deleteMovie(@PathVariable("id") ObjectId id) {
		return engine.getMovieById(id);
	}

}
