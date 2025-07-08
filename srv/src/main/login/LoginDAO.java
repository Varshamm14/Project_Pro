package customer.project_pro;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class LoginDAO {
    @PersistenceContext
    private EntityManager entityManager;

    // New JV Posting
    public String newLogin(String login) {
        try {
            StoredProcedureQuery sp_Login = entityManager.createStoredProcedureQuery("ADD_LOGIN");

            sp_Login.registerStoredProcedureParameter("IN_PARAM", String.class, ParameterMode.IN);
            sp_Login.registerStoredProcedureParameter("EX_MESSAGE", String.class, ParameterMode.OUT);

            sp_Login.setParameter("IN_PARAM", login);
            sp_Login.execute();

            return (String) sp_Login.getOutputParameterValue("EX_MESSAGE");

        } catch (Exception exception) {
            throw exception;
        }
    }
}
