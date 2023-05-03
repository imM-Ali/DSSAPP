package com.web.dssapp.repository;


import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.web.dssapp.model.Movie;

public interface MongoRepo extends MongoRepository<Movie, Integer> {
	
	@Aggregation(pipeline = { "{$group: { _id: '', total: {$max: $_id }}}" })
	public int max();

}
