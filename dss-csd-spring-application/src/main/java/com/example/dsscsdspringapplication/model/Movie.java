// Java Program to illustrate Book File
 
// Importing required classes
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
// Annotations
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Movie")
 
// Class
public class Movie
{
 
    // Attributes
    @Id private int id;
    private String MovieName;
    private String CategoryName;
}