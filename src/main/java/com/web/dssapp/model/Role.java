package com.web.dssapp.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection="role")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false, unique=true)
    private String name;

    @ManyToMany(mappedBy="roles")
    private List<User> users;
    
    public Role(int id, String name) {
    	this.id = id;
    	this.name = name;
    }
}
