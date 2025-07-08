package customer.project_pro;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@Entity
public class Login {
	@Id
	private String username;
	private String password;
	private Integer status;
}