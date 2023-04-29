package com.web.dssapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.dssapp.model.Movie;
import com.web.dssapp.model.Role;
import com.web.dssapp.service.RoleService;

import jakarta.validation.Valid;

@Controller
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/roles")
	public String home(Model model) {			
		List<Role> allRoles = roleService.getAllRoles();		
		model.addAttribute("movies", allRoles);
		return "rolespage";
	}
	
	@GetMapping("/addRole")
	public String addRole(Model model) {
		model.addAttribute("role", new Role());
		return "addrolepage";
	}

	@PostMapping("/addRole")
	public String addRole(@Valid Role role, BindingResult bindingResult, RedirectAttributes atr) {
		if (bindingResult.hasErrors()) {
			return "addrolepage";
		}

		roleService.addRole(role);
		atr.addFlashAttribute("status", "Role saved successfully with movie Id: " + role.getId());
		return "redirect:/roles";

	}
	
	@GetMapping("/editRole/{id}")
	public String editRole(@PathVariable("id") int id, Model model, RedirectAttributes redirAttrs) {
		Optional<Role> oldRole = roleService.getRoleById(id);
		if (oldRole != null) {
			model.addAttribute("role", oldRole.get());
			return "editrolepage";
		} else {
			redirAttrs.addFlashAttribute("status", "Role does not exist");
			return "redirect:/editrole";
		}
	}

	@PostMapping("/updateRole/{id}")
	public String saveEditedRole(@PathVariable("id") int id, @Valid Role roleDTO, BindingResult res, RedirectAttributes redirAttrs, Model model) {	
		if(res.hasErrors()) {
			roleDTO.setId(id);
			model.addAttribute("role", roleDTO);
			return "editrolepage";
		}
		Optional<Role> oldRole = roleService.getRoleById(id);
		if (oldRole != null) {
			Role existingRole = oldRole.get();
			roleService.updateRole(existingRole, roleDTO);
			redirAttrs.addFlashAttribute("status", "Role ID: " + id + " saved successfully!");
			return "redirect:/roles" ;
		} else {
			redirAttrs.addFlashAttribute("status", "Role ID does not exist");
			return "redirect:/editmovie/{"+id+"}";
		}
	}

	
	@GetMapping("/deleterole/{id}")
	public String deleteRole(@PathVariable("id") int id, RedirectAttributes redirAttrs) {
		
		String msg = roleService.deleteRoleById(id);
		redirAttrs.addFlashAttribute("status", msg);
		return "redirect:/roles";
	}

	@GetMapping("/roleDetail/{id}")
	public String roleDetail(@PathVariable("id") int id, Model model) {
		model.addAttribute("movie", roleService.getRoleById(id).get());
		
		// implement here somthing to show all the users from that role
		
		return "detailrolepage";		
	}

}
