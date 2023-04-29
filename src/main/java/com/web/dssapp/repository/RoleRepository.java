package com.web.dssapp.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.web.dssapp.model.Role;


public interface RoleRepository extends MongoRepository<Role, Integer> {
	@Query("{name:'?0'}")
    Role findRoleByName(String name);
	
	@Query("{id:'?0'}")
    Optional<Role> findRoleById(int id);

	@SuppressWarnings("unchecked")
	Role save(Role role);
	
	@Aggregation(pipeline = { "{$group: { _id: '', total: {$max: $_id }}}" })
	public int maxid();

	void deleteById(int id);
}
