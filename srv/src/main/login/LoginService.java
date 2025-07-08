package customer.project_pro;

import customer.project_pro.dao.LoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginDAO loginDAO;

    public int checkLogin(String username, String password) {
        return loginDAO.callLoginProcedure(username, password);
    }
}
