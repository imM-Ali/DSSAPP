package com.web.dssapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.web.dssapp.model.movie;

public interface mongoRepo extends MongoRepository<movie, Integer> {

}
