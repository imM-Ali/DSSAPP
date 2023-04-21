package com.web.dssapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.dssapp.model.Movie;
import com.web.dssapp.repository.MongoRepo;

@Controller
public class BaseController {

	@Autowired
	MongoRepo _db;
	
	@GetMapping("/")
    public String home(Model model){
		List<Movie> allMovies = _db.findAll();
		model.addAttribute("movies", allMovies);
        return "index";
    }
	
	
	
	
}
