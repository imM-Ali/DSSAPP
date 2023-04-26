package com.web.dssapp.model;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection="users")
public class User
{
   

    @Field(targetType = FieldType.OBJECT_ID)
    private ObjectId id;
    private String name;
    private String email;
    private String password;
    private List<Role> roles = new ArrayList<>();
}
