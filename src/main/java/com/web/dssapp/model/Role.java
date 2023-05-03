package com.web.dssapp.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Setter
@Getter
@Data
@Document(collection="roles")
public class Role
{
    @Id 
    private int _id;
    private String name;    
    
    
    public Role(int id, String name) {
    	this._id = id;
    	this.name = name;
    }
    public Role() {
    	
    }

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
}
