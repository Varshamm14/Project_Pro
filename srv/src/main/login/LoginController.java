package customer.project_pro;

import customer.project_pro.model.Login;
import customer.project_pro.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService; //

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        System.out.println("USERNAME from UI: " + login.getUsername());
        System.out.println("PASSWORD from UI: " + login.getPassword());

        boolean isValid = loginService.loginCheck(login.getUsername(), login.getPassword());
        if (isValid) {
            return ResponseEntity.ok("Login success");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

}
