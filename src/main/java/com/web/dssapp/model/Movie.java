package com.web.dssapp.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.var;

@Document(collection = "moviesData")
public class Movie {
    @Id
    private ObjectId id;
    private String Genre_Title;    
    private int movie_Id;

    // default constructor
    public Movie() {}

    // constructor with arguments
    public Movie(ObjectId id, String Genre_Title, int movie_Id) {
        this.id = id;
        this.Genre_Title = Genre_Title;
        this.movie_Id = movie_Id;       
    }
    
    @Override
    public String toString() {
    	
		return "1)"+this.id+"2)"+this.Genre_Title+"3)"+this.movie_Id;
    	
    }

	public String getGenre_Title() {
		return Genre_Title;
	}

	public void setGenre_Title(String genre_Title) {
		Genre_Title = genre_Title;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public int getMovie_Id() {
		return movie_Id;
	}

	public void setMovie_Id(int movie_Id) {
		this.movie_Id = movie_Id;
	}

	public String getGenre() {
		return Genre_Title.split("\\t")[0];
	}


	public String getTitle() {
		return Genre_Title.split("\\t")[1];
	}


 
    
}
