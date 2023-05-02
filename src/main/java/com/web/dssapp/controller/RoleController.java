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

import com.web.dssapp.model.Role;
import com.web.dssapp.service.RoleService;

import jakarta.validation.Valid;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	
	@GetMapping("/roles")
    public String getAllRoles(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "rolespage";
    }

	@GetMapping("/addRole")
	public String addRole(Model model) {
		model.addAttribute("role", new Role());
		return "addrolepage";
	}

	@PostMapping("/addRole")
	public String addrole(@Valid Role role, BindingResult bindingResult, RedirectAttributes atr) {
		if (bindingResult.hasErrors()) {
			return "addrolepage";
		}

		roleService.addRole(role);
		atr.addFlashAttribute("status", "Role saved successfully with role Id: " + role.get_id());
		return "redirect:/roles";

	}

	@GetMapping("/viewroledetails/{id}")
	public String viewRoleDetails(@PathVariable("id") int id, Model model) {
		model.addAttribute("role", roleService.getRoleById(id).get());
		return "roledetailpage";

	}

	@GetMapping("/editRole/{id}")
	public String editRole(@PathVariable("id") int id, Model model, RedirectAttributes redirAttrs) {
		Optional<Role> oldRole = roleService.getRoleById(id);
		if (oldRole.isPresent()) {
			Role role = oldRole.get();
			model.addAttribute("role", role);
			return "editrolepage";
		} else {
			redirAttrs.addFlashAttribute("status", "Something went wrong");
			return "redirect:/editRole";
		}
	}	

	@PostMapping("/editRole/{id}")
	public String editedRole(@PathVariable("id") int id, @Valid Role roleDTO, BindingResult res, RedirectAttributes redirAttrs, Model model) {	
		if(res.hasErrors()) {
			roleDTO.set_id(id);
			model.addAttribute("role", roleDTO);
			return "editrolepage";
		}
		Optional<Role> oldRole = roleService.getRoleById(id);
		if (oldRole.isPresent()) {
			Role existingRole = oldRole.get();
			roleService.updateRole(existingRole, roleDTO);
			redirAttrs.addFlashAttribute("status", "Role ID: " + id + " saved successfully!");
			return "redirect:/roles" ;
		} else {
			redirAttrs.addFlashAttribute("status", "Role ID does not exist");
			return "redirect:/editRole/{"+id+"}";
		}
	}

	@GetMapping("/deleteRole/{id}")
	public String deleteRole(@PathVariable("id") int id, RedirectAttributes redirAttrs) {

		String msg = roleService.deleteRoleById(id);
		redirAttrs.addFlashAttribute("status", msg);
		return "redirect:/roles";
	}

}
