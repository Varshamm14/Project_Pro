package customer.project_pro;

import customer.project_pro.dao.LoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

      @Autowired
    private DataSource dataSource;

    public boolean loginCheck(String username, String password) {
        try (Connection con = dataSource.getConnection();
             CallableStatement cs = con.prepareCall("{ CALL CHECK_LOGIN(?, ?, ?) }")) {

            cs.setString(1, username);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();

            int result = cs.getInt(3);
            System.out.println("Login check result: " + result); // ✅ Add this

            return result == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}