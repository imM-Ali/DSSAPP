package com.web.dssapp.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "movieslatest")
public class Movie {
	@Id
	private int _id;
	private String txt;
	@NotBlank(message = "Title cannot be empty")
	private String title;
	private String directedBy;
	@NotBlank(message = "Cast cannot be empty")
	private String starring;
	private double avgRating;
	private String dateAdded = new Date().toString();

	public Movie() {

	}

	public Movie(int _id, String txt, String title, String directedBy, String starring, double avgRating) {
		super();
		this._id = _id;
		this.txt = txt;
		this.title = title;
		this.directedBy = directedBy;
		this.starring = starring;
		this.avgRating = avgRating;

	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirectedBy() {
		return directedBy;
	}

	public void setDirectedBy(String directedBy) {
		this.directedBy = directedBy;
	}

	public String getStarring() {
		return starring;
	}

	public void setStarring(String starring) {
		this.starring = starring;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

}
