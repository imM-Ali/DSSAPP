package com.web.dssapp.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.web.dssapp.model.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
	
	@Query("{name:'?0'}")
    User findUserByName(String name);
	
	@Query("{name:'?0'}")
    User findUserByEmail(String email);
}
