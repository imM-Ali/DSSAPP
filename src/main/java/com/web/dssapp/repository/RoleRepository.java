package com.web.dssapp.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.web.dssapp.model.Role;


public interface RoleRepository extends MongoRepository<Role, Integer> {
	@Query("{name:'?0'}")
    Role findRoleByName(String name);

	@SuppressWarnings("unchecked")
	Role save(Role role);
	

	void deleteById(int id);

	@Aggregation(pipeline = { "{$group: { _id: '', total: {$max: $_id }}}" })
	public int maxid();
	
	
}
