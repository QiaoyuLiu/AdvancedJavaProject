package fr.epita.quiz.services;

/**
 * <h3>Description</h3>
 * <p>This class allows to build login where clause</p>
 *
 * <h3>Usage</h3>
 * <p>This class should be used as follows:
 *   <pre><code>LoginDao dao = new LoginDao()
 *   dao.getWhereClauseBuilder(T);
 *   </code></pre>
 * </p>
 *
 * @since $${version}
 * @see See also $${link}
 * @author ${user}
 *
 * ${tags}
 */
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import fr.epita.quiz.datamodel.Login;
import fr.epita.quiz.datamodel.Question;

public class LoginDao extends GenericORMDao<Login>{
	@Inject
	@Named("questionQuery")
	String query;
	@Override
	protected WhereClauseBuilder<Question> getWhereClauseBuilder(Login entity) {
		// TODO Auto-generated method stub
		final WhereClauseBuilder<Question> wcb = new WhereClauseBuilder<>();
		wcb.setQueryString(query);
		final Map<String, Object> parameters = new LinkedHashMap<>();
		parameters.put("login", entity.getLogin());
		parameters.put("password", entity.getPassword());
		wcb.setParameters(parameters);
		return wcb;

	}


}
