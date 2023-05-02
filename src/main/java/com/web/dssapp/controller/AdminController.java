package com.web.dssapp.controller;

import com.web.dssapp.dto.UserDto;
import com.web.dssapp.model.User;
import com.web.dssapp.service.UserService;
import org.springframework.ui.Model;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;


	@GetMapping("/admin")
	public String loginForm() {		
		return "adminhomepage";
	}

	@GetMapping("/users/{pageNumber}")
	public String home(@PathVariable(value = "pageNumber", required = false) int pageNumber, Model model) {  
		Page<User> pagedUsers = userService.findAllUsersP(pageNumber, 10, Sort.by(Sort.Direction.ASC, "_id"));

		List<User> allUsers = pagedUsers.getContent();
		model.addAttribute("users", allUsers);
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", pagedUsers.getTotalPages());
		model.addAttribute("totalItems", pagedUsers.getTotalElements());

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

}
