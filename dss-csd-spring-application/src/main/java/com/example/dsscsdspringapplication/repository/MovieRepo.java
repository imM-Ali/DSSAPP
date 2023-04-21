// Java Program to Illustrate MovieRepo File
 
import com.example.dsscsdspringapplication.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
 
public interface MovieRepo
    extends MongoRepository<Movie, Integer> {
}