package customer.project_pro;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {
     @GetMapping("/hello")
    public String hello() {
        return "Hello from Project_Pro!";
    }
    
}
