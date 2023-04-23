package com.web.dssapp.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.web.dssapp.model.Role;


public interface RoleRepository extends MongoRepository<Role, Long> {
	@Query("{name:'?0'}")
    Role findRoleByName(String name);

	@SuppressWarnings("unchecked")
	Role save(Role role);
}
