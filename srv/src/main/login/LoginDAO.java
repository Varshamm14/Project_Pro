package customer.project_pro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class LoginDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int callLoginProcedure(String username, String password) {
        return jdbcTemplate.execute((Connection con) -> {
            try (CallableStatement cs = con.prepareCall("{ CALL \"login::LOGIN_CHECK\"(?, ?, ?) }")) {
                cs.setString(1, username);
                cs.setString(2, password);
                cs.registerOutParameter(3, Types.INTEGER);
                cs.execute();
                return cs.getInt(3);  // STATUS
            }
        });
    }
}
