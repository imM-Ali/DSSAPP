package com.web.dssapp.controller;

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

	/*
	 * @GetMapping("/roles/{pageNumber}") public String home(@PathVariable(value =
	 * "pageNumber", required = false) int pageNumber, Model model) { // Page<Role>
	 * pagedMovies = //
	 * roleService.findAllRolesP(pageNumber,10,Sort.by(Sort.Direction.ASC, "_id"));
	 * 
	 * List<Role> allRoles = pagedMovies.getContent(); model.addAttribute("roles",
	 * allRoles); model.addAttribute("currentPage", pageNumber);
	 * model.addAttribute("totalPages", pagedMovies.getTotalPages());
	 * model.addAttribute("totalItems", pagedMovies.getTotalElements());
	 * 
	 * return "rolespage"; }
	 */

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
		return "redirect:/role";

	}

	@GetMapping("/viewroledetails/{id}")
	public String viewRoleDetails(@PathVariable("id") int id, Model model) {
		model.addAttribute("role", roleService.getRoleById(id).get());
		return "roledetailpage";

	}

	@GetMapping("/editrole/{id}")
	public String editRole(@PathVariable("id") int id, Model model, RedirectAttributes redirAttrs) {
		Optional<Role> oldRole = roleService.getRoleById(id);
		if (oldRole != null) {
			model.addAttribute("role", oldRole.get());
			return "editrolepage";
		} else {
			redirAttrs.addFlashAttribute("status", "Something went wrong");
			return "redirect:/editrole";
		}
	}

	@PostMapping("/editrole/{id}")
	public String editedRole(@PathVariable("id") int id, @Valid Role roleDTO, BindingResult res, RedirectAttributes redirAttrs, Model model) {	
		if(res.hasErrors()) {
			roleDTO.set_id(id);
			model.addAttribute("role", roleDTO);
			return "editrolepage";
		}
		Optional<Role> oldMov = roleService.getRoleById(id);
		if (oldMov != null) {
			Role existingRole = oldMov.get();
			roleService.updateRole(existingRole, roleDTO);
			redirAttrs.addFlashAttribute("status", "Role ID: " + id + " saved successfully!");
			return "redirect:/roles/1" ;
		} else {
			redirAttrs.addFlashAttribute("status", "Role ID does not exist");
			return "redirect:/editrole/{"+id+"}";
		}
	}

	@GetMapping("/deleterole/{id}")
	public String deleteRole(@PathVariable("id") int id, RedirectAttributes redirAttrs) {

		String msg = roleService.deleteRoleById(id);
		redirAttrs.addFlashAttribute("status", msg);
		return "redirect:/roles";
	}

}
