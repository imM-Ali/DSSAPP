package com.web.dssapp.repository;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.web.dssapp.model.Movie;

public interface MongoRepo extends MongoRepository<Movie, Integer> {
	

	

}
