package customer.project_pro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

@Service
public class LoginService {

    @Autowired
    LoginDAO loginDAO;

     @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean login(String userId, String password) {
        return jdbcTemplate.execute((Connection connection) -> {
            CallableStatement cs = connection.prepareCall("{ CALL LOGIN_PROCEDURE(?, ?, ?) }");
            cs.setString(1, userId);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            int result = cs.getInt(3);
            return result == 1;
        });
    }
}