package com.web.dssapp.repository;


import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.web.dssapp.model.User;

public interface UserRepository extends MongoRepository<User, Integer> {
	
	@Query("{username:'?0'}")
    User findUserByusername(String username);
	
	@Query("{email:'?0'}")
    User findUserByEmail(String email);
	
	@Query("{role:'?0'}")
    User findUserByRole(String role);
	
	@Aggregation(pipeline = { "{$group: { _id: '', total: {$max: $_id }}}" })
	public int maxid();
}
