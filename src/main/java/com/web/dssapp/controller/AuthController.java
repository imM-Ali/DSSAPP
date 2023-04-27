package com.web.dssapp.controller;
  
import com.web.dssapp.dto.UserDto; 
import com.web.dssapp.model.User; 
import com.web.dssapp.service.UserService;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
  
@Controller 
public class AuthController {
  
	@Autowired
	private UserService userService;
	
	@GetMapping("/login") 
	public String loginForm(Model model) {
		model.addAttribute("user", new UserDto());
		return "login"; 
	}
	
  
	// handler method to handle user registration request
  
	@GetMapping("/adduserpage") 
	public String showRegistrationForm(Model model){
		UserDto user = new UserDto(); 
		model.addAttribute("user", user);
		return  "register"; 
	}
  
	// handler method to handle register user form submit request
  
	@PostMapping("/adduserpage") 
	public String registration(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model){ 
		User existing = userService.findByEmail(user.getEmail()); 
		if (existing != null) {
			result.rejectValue("email", null, "There is already an account registered with that email"); 
		} if (result.hasErrors()) { 
			model.addAttribute("user", user);
			return "register"; 
		}
		userService.saveUser(user); 
		return "redirect:/register?success"; 
	}
}
 