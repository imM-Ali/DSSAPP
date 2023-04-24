package com.web.dssapp.controller;

import com.web.dssapp.model.Movie;
import com.web.dssapp.service.MovieService;
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
		Optional<Movie> found= engine.getMovieById(movie.getMovie_Id());
		if( found != null) {			
			redirAttrs.addFlashAttribute("status", "Movie ID already exists");
			return "redirect:/addMovie";
		}else {
			engine.addMovie(movie);
			redirAttrs.addFlashAttribute("status", "Movie ID: "+movie.getMovie_Id()+"saved successfully!");
			return "redirect:/addMovie";
		}
		
	}

	@GetMapping("/movies/{id}")
	public Optional<Movie> getMovieById(@PathVariable("id") int id) {
		return engine.getMovieById(id);
	}

	@DeleteMapping("/movie/{id}")
	public String deleteMovie(@PathVariable("id") int id) {
		return engine.deleteMovieById(id);
	}

}
