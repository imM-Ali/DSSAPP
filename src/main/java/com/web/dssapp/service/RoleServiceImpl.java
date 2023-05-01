package com.web.dssapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.web.dssapp.model.Role;
import com.web.dssapp.model.User;
import com.web.dssapp.repository.RoleRepository;

public class RoleServiceImpl implements RoleService{
	
	private RoleRepository roleRepository;
	private int maxid;

	@Override
	public Boolean addRole(Role role) {
		try {
			maxId();
			role.setId(maxid + 1);
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
		// TODO Auto-generated method stub
		return roleRepository.findRoleByName(role);
	}
	
	@Override
	public Optional<Role> getRoleById(int id) {
		// TODO Auto-generated method stub
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
	public void maxId() {
		this.maxid = roleRepository.maxid();
		
	}

	@Override
	public Boolean updateRole(Role role, Role roleDTO) {
		try {
			role.setId(roleDTO.getId());
			role.setName(roleDTO.getName());
			List<User> list = new ArrayList<User>();
			for(User user : list) {
				user.setRole(roleDTO.getName());
			}
			roleRepository.save(role);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

}
