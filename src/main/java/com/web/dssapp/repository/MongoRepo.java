package com.web.dssapp.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.web.dssapp.model.Movie;

public interface MongoRepo extends MongoRepository<Movie, ObjectId> {
	

	

}
