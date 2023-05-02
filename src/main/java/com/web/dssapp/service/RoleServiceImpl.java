package com.web.dssapp.service;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dssapp.model.Role;
import com.web.dssapp.model.User;
import com.web.dssapp.repository.RoleRepository;


@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;
	

	@Override
	public Boolean addRole(Role role) {
		try {		
			//by default every new sign up will be classes as a normal user
			role.set_id(maxid() + 1);
			roleRepository.save(role);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Role getRoleByName(String role) {		
		return roleRepository.findRoleByName(role);
	}
	
	@Override
	public Optional<Role> getRoleById(Integer id) {
		return roleRepository.findRoleById(id);
	}

	@Override
	public String deleteRoleById(int id) {
		try {
			roleRepository.deleteById(id);
			return "Role deleted successfully";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public Boolean updateRole(Role role, Role roleDTO) {
		try {
			role.set_id(roleDTO.get_id());
			role.setName(roleDTO.getName());
			roleRepository.save(role);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int maxid() {
		return roleRepository.maxid();
	}
	


}