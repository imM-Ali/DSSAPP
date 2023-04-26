package com.web.dssapp.model;



import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

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
	@Field(targetType = FieldType.OBJECT_ID)
    private ObjectId id;
    private String name;
    private List<User> users;
    
    public Role(ObjectId id, String name) {
    	this.id = id;
    	this.name = name;
    }
}
