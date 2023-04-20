package com.web.dssapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.dssapp.model.movie;
import com.web.dssapp.repository.mongoRepo;

@Controller
public class baseController {

	@Autowired
	mongoRepo _db;
	
	@GetMapping("/")
    public String home(Model model){
		List<movie> allMovies = _db.findAll();
		model.addAttribute("movies", allMovies);
        return "index";
    }
	
	
	
	
}
