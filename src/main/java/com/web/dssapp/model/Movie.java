package com.web.dssapp.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "moviesData")
public class Movie {
    @Id
    private ObjectId id;
    private String title;
    private String txt;
    private String directedBy;
    private String starring;
    private double avgRating;
    
    @Override
    public String toString() {
    	
		return "1)"+this.id.toString()+"2)"+this.title+"3)"+this.txt;
    	
    }


 
    
}
