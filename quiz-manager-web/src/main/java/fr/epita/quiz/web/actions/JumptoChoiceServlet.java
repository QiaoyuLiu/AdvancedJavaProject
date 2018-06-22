package fr.epita.quiz.web.actions;



import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.epita.quiz.web.services.QuestionsServices;
/**
 * <h3>Description</h3>
 * <p>This class allows to jump to editchoice.jsp</p>
 *
 * <h3>Usage</h3>
 * <p>This class should be used as follows:
 *   <pre><code><form action="jumptochoice" method="post">
 * 
 *   </code></pre>
 * </p>
 *
 * @since $${version}
 * @see See also $${link}
 * @author ${user}
 *
 * ${tags}
 */

public class JumptoChoiceServlet extends SpringServlet{
	@Inject
	QuestionsServices qs;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		qs.sendQuestionMsg(request, response);
			response.sendRedirect("editchoice.jsp");
		}
	}