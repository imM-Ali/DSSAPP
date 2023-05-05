package com.web.dssapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.dssapp.dto.UserDto;
import com.web.dssapp.model.User;
import com.web.dssapp.service.RoleService;
import com.web.dssapp.service.UserService;
import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@GetMapping("/edituser/{admin}&{id}")
	public String editUser(@PathVariable("id") int id,@PathVariable("admin") int admin, Model model, RedirectAttributes redirAttrs) {
		Optional<User> oldUser = userService.findUserById(id);
		if(oldUser != null){
			if(admin==1){
			model.addAttribute("user", oldUser.get());
			model.addAttribute("allRoles", roleService.getAllRoles());
			return "adminedituserpage";
			}else{
				model.addAttribute("user", oldUser.get());
				return "edituserpage";
			}

		}else {
			redirAttrs.addFlashAttribute("status", "Something went wrong");
			return "redirect:/edituser";
		}
		
	}

	@PostMapping("/edituser/{admin}&{id}")
	public String saveEditedUser(@PathVariable("id") int id,@PathVariable("admin") int admin, @ModelAttribute("user") @Valid UserDto userDTO,
			BindingResult res, RedirectAttributes attr) {
		userDTO.set_id(id);
		Optional<User> oldUser = userService.findUserById(id);
		User existingUser = oldUser.get();
		// if there is 1 error and that is from password field, still continue bcz user
		// does not want to change pass
		boolean validationCondition1 = res.getAllErrors().size() == 1 && res.getFieldValue("password").equals("");
		// if there are no errors, continue saving if
		boolean validationCondition2 = !res.hasErrors();
		if (validationCondition1 || validationCondition2) {
			if (validationCondition1) {
				userDTO.setPassword(existingUser.getPassword());
			}
			userService.updateUser(existingUser, userDTO);
			attr.addFlashAttribute("status", "Saved successfully, will reflect on next login");
			if(admin==1){
				return "redirect:/users/1";
			}else{
				return "redirect:/viewuserdetails/" + id;
			}
			
			
		}
		// if there are errors and the previous 2 conditions were not met, then return
		// the view with error fields now showing
		else {
			return "edituserpage";
		}

	}

	@GetMapping("/deleteuser/{id}")
	public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirAttrs) {

		// String msg = userService.deleteMovieById(id);
		// redirAttrs.addFlashAttribute("status", msg);
		return "redirect:/movies/1";
	}

}
