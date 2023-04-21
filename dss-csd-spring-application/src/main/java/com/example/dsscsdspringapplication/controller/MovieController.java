import com.example.dsscsdspringapplication.model.Movie;
import com.example.dsscsdspringapplication.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
// Annotation
@RestController
 
// Class
public class MovieController {
 
    @Autowired
    private MovieRepo repo;
 
    @PostMapping("/addMovie")
    public String saveMovie(@RequestBody Movie movie){
        repo.save(movie);
       
        return "Added Successfully";
    }
 
    @GetMapping("/findAllMovies")
    public List<Movie> getMovies() {
       
        return repo.findAll();
    }
 
    @DeleteMapping("/delete/{id}")
    public String deleteMovie(@PathVariable int id){
        repo.deleteById(id);
       
        return "Deleted Successfully";
    }
 
}