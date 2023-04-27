package com.web.dssapp.controller;

import com.web.dssapp.config.CustomUserDetails;
import com.web.dssapp.model.Movie;
import com.web.dssapp.model.User;
import com.web.dssapp.service.MovieService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.Console;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.logging.ConsoleHandler;

@Controller
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/movies/{pageNumber}")
	public String home(@PathVariable(value ="pageNumber", required=false) int pageNumber,Model model) {	
		final User CURRENT_USER = ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getContext();
		Page<Movie> pagedMovies = movieService.getAllMovies(pageNumber,50,Sort.by(Sort.Direction.ASC, "_id"));
		List<Movie> allMovies = pagedMovies.getContent();
		model.addAttribute("currentuser", CURRENT_USER);
		model.addAttribute("movies", allMovies);
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", pagedMovies.getTotalPages());
		model.addAttribute("totalItems", pagedMovies.getTotalElements());
		return "moviespage";
	}	


	@GetMapping("/addMovie")
	public String addMovie(Model model) {
		model.addAttribute("movie", new Movie());
		return "addmoviepage";
	}

	@PostMapping("/addMovie")
	public String addmovie(@Valid Movie movie, BindingResult bindingResult, RedirectAttributes atr) {
		if (bindingResult.hasErrors()) {
			return "addmoviepage";
		}

		movieService.addMovie(movie);
		atr.addFlashAttribute("status", "Movie saved successfully with movie Id: " + movie.get_id());
		return "redirect:/movies/1";

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
			return "redirect:/movies/1" ;
		} else {
			redirAttrs.addFlashAttribute("status", "Movie ID does not exist");
			return "redirect:/editmovie/{"+id+"}";
		}
	}

	
	@GetMapping("/deletemovie/{id}")
	public String deleteMovie(@PathVariable("id") int id, RedirectAttributes redirAttrs) {
		
		String msg = movieService.deleteMovieById(id);
		redirAttrs.addFlashAttribute("status", msg);
		return "redirect:/movies/1";
	}

	@GetMapping("/movieDetail/{id}")
	public String movieDetail(@PathVariable("id") int id, Model model) {
		model.addAttribute("movie", movieService.getMovieById(id).get());
		return "detailmoviepage";		
	}
}
