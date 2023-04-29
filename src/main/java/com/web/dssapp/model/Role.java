package com.web.dssapp.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@Document(collection="role")
public class Role
{
    @Id    
    private int id;
    private String name;
    
    public Role(int id, String name) {
    	this.id = id;
    	this.name = name;
    }

	public Role() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String string) {
		// TODO Auto-generated method stub
		
	}
}
