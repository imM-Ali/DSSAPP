package com.web.dssapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.dssapp.dto.UserDto;
import com.web.dssapp.model.User;
import com.web.dssapp.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/users/{pageNumber}")
	public String home(@PathVariable(value ="pageNumber", required=false) int pageNumber,Model model) {
		//Page<User> pagedMovies = userService.findAllUsersP(pageNumber,10,Sort.by(Sort.Direction.ASC, "_id"));
		/*
		 * List<User> allUsers = pagedMovies.getContent(); model.addAttribute("users",
		 * allUsers); model.addAttribute("currentPage", pageNumber);
		 * model.addAttribute("totalPages", pagedMovies.getTotalPages());
		 * model.addAttribute("totalItems", pagedMovies.getTotalElements());
		 */
		return "userspage";
	}	


	@GetMapping("/addUser")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		return "adduserpage";
	}

	@PostMapping("/addUser")
	public String adduser(@Valid UserDto user, BindingResult bindingResult, RedirectAttributes atr) {
		if (bindingResult.hasErrors()) {
			return "adduserpage";
		}

		userService.saveUser(user);
		atr.addFlashAttribute("status", "User saved successfully with user Id: " + user.get_id());
		return "redirect:/user/1";

	}

	@GetMapping("/viewuserdetails/{id}")
	public String viewUserDetails(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userService.findUserById(id).get());
		return "userdetailpage";
		
	}
	
	
	@GetMapping("/edituser/{id}")
	public String editUser(@PathVariable("id") int id, Model model, RedirectAttributes redirAttrs) {
		Optional<User> oldUser = userService.findUserById(id);
		if (oldUser != null) {
			model.addAttribute("user", oldUser.get());
			return "edituserpage";
		} else {
			redirAttrs.addFlashAttribute("status", "User does not exist");
			return "redirect:/edituser";
		}
	}

	@PostMapping("/updateuser/{id}")
	public String saveEditedUser(@PathVariable("id") int id, UserDto userDto, RedirectAttributes redirAttrs) {
		Optional<User> oldUser = userService.findUserById(id);
		if (oldUser != null) {
			User existingMovie = oldUser.get();
			userService.updateUser(existingMovie, userDto);
			redirAttrs.addFlashAttribute("status", "User ID: " + id + " saved successfully!");
			return "redirect:/users/1" ;
		} else {
			redirAttrs.addFlashAttribute("status", "User ID does not exist");
			return "redirect:/edituser/{"+id+"}";
		}
	}

	
	@GetMapping("/deleteuser/{id}")
	public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirAttrs) {
		
		//String msg = userService.deleteMovieById(id);
		//redirAttrs.addFlashAttribute("status", msg);
		return "redirect:/movies/1";
	}

}
