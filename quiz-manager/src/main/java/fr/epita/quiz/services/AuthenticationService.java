/**
 * Ce fichier est la propriété de Thomas BROUSSARD
 * Code application :
 * Composant :
 */
package fr.epita.quiz.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.Login;

/**
 * <h3>Description</h3>
 * <p>This class allows to ...</p>
 *
 * <h3>Usage</h3>
 * <p>This class should be used as follows:
 *   <pre><code>${type_name} instance = new ${type_name}();</code></pre>
 * </p>
 *
 * @since $${version}
 * @see See also $${link}
 * @author ${user}
 *
 * ${tags}
 */

@Repository

public class AuthenticationService {

LoginDao logd = new LoginDao();
	public boolean authenticate(String login, String password) {
		// TODO get a real authentication later
		Login log = new Login(login,password);
		List<Login> name = logd.search(log);
		if (name!=null&&!name.isEmpty())
		return true;
		else return false;
	}

}
