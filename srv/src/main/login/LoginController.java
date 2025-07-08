package customer.project_pro;

import customer.project_pro.model.LoginRequest;
import customer.project_pro.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    
    @Autowired
    private LoginService loginService;

    @PostMapping
    public String login(@RequestBody LoginRequest request) {
        int status = loginService.checkLogin(request.getUsername(), request.getPassword());
        return (status == 1) ? "Login Successful" : "Login Failed";
    }
}