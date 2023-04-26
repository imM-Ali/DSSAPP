package com.web.dssapp.controller;

import com.web.dssapp.model.Movie;
import com.web.dssapp.service.MovieService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping("/movies")
	public String home(Model model) {

		model.addAttribute("movies", movieService.getAllMovies());
		return "moviespage";
	}	


	@GetMapping("/addMovie")
	public String saveMovie(Model model) {
		model.addAttribute("movie", new Movie());
		return "addmoviepage";
	}

	@PostMapping("/addMovie")
	public String saveMovie(@Valid Movie movie, BindingResult bindingResult, RedirectAttributes atr) {
		if (bindingResult.hasErrors()) {
			return "addmoviepage";
		}

		movieService.addMovie(movie);
		atr.addFlashAttribute("status", "Movie saved successfully with movie Id: " + movie.get_id());
		return "redirect:/movies";

	}

	@GetMapping("/editmovie/{id}")
	public String editMovie(@PathVariable("id") int id, Model model, RedirectAttributes redirAttrs) {
		Optional<Movie> oldMov = movieService.getMovieById(id);
		if (oldMov != null) {
			model.addAttribute("movie", oldMov.get());
			return "editmoviepage";
		} else {
			redirAttrs.addFlashAttribute("status", "Movie does not exist");
			return "redirect:/editmovie";
		}
	}

	@PostMapping("/updateMovie/{id}")
	public String saveEditedMovie(@PathVariable("id") int id, Movie movieDTO, RedirectAttributes redirAttrs) {
		Optional<Movie> oldMov = movieService.getMovieById(id);
		if (oldMov != null) {
			Movie existingMovie = oldMov.get();
			movieService.updateMovie(existingMovie, movieDTO);
			redirAttrs.addFlashAttribute("status", "Movie ID: " + id + " saved successfully!");
			return "redirect:/movies" ;
		} else {
			redirAttrs.addFlashAttribute("status", "Movie ID does not exist");
			return "redirect:/editmovie/{"+id+"}";
		}
	}

	
	@GetMapping("/deletemovie/{id}")
	public String deleteMovie(@PathVariable("id") int id, RedirectAttributes redirAttrs) {
		String msg = movieService.deleteMovieById(id);
		redirAttrs.addFlashAttribute("status", msg);
		return "redirect:/movies";
	}

}
