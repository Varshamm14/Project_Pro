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
        boolean isValid = loginService.loginCheck(login.getUsername(), login.getPassword());
        if (isValid) {
            return ResponseEntity.ok("Login Successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
        }
    }
}
