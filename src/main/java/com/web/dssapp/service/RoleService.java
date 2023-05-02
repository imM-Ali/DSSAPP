package com.web.dssapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.web.dssapp.model.Role;	

	@Service
	public interface RoleService {

		public Boolean addRole(Role role);
		public List<Role> getAllRoles();
		public Role getRoleByName(String role);
		public Optional<Role> getRoleById(int id);
		public Boolean deleteRoleById(int id);		
		
	}
	
