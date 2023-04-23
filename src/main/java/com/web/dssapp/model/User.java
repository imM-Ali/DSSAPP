package com.web.dssapp.model;

import lombok.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;





@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection="user")
public class User
{
    private static final long serialVersionUID = 1L;

    @Id
  
    private long id;

    
    private String name;

    
    private String email;

    
    private String password;

  
    private List<Role> roles = new ArrayList<>();
    
    public User(int id, String name, String email, String password) {
    	this.id = id;
    	this.name = name;
    	this.email = email;
    	this.password = password;
    }

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Role> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setEmail(String email2) {
		// TODO Auto-generated method stub
		
	}

	public void setPassword(String encode) {
		// TODO Auto-generated method stub
		
	}

	public void setRoles(List<Role> asList) {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}



}
