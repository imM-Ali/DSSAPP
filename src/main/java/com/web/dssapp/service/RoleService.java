package com.web.dssapp.service;

import java.util.List;
import java.util.Optional;

import com.web.dssapp.model.Role;	

	
	public interface RoleService {

		public Boolean addRole(Role role);
		public List<Role> getAllRoles();
		public Role getRoleByName(String role);
		public String deleteRoleById(int id);	
		public Boolean updateRole(Role role, Role roleDTO);
		Optional<Role> getRoleById(Integer id);
		public int maxid();
	}
	
