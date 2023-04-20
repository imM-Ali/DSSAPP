package com.web.dssapp.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection="moviesData")
public class movie {
	
	@Id
	private String Id;
	private int movieId;
	private String titleAndGenre;
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getTitleAndGenre() {
		return titleAndGenre;
	}
	public void setTitleAndGenre(String titleAndGenre) {
		this.titleAndGenre = titleAndGenre;
	}

}
