package fr.epita.quiz.web.actions;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.services.MCQChoiceDAO;
import fr.epita.quiz.services.QuestionDAO;
import fr.epita.quiz.web.services.QuestionsServices;
/**
 * <h3>Description</h3>
 * <p>This class allows to creat a choice</p>
 *
 * <h3>Usage</h3>
 * <p>This class should be used as follows:
 *   <pre><code><form action="choicecreate" method="post">
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
public class MCQservletCreate extends SpringServlet{
	@Inject
	MCQChoiceDAO mcqChoiceDAO;
	@Inject
	QuestionDAO questionDAO;
	@Inject
	QuestionsServices qs;

	@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MCQChoice mcqc = qs.getMCQ(request,response);
        mcqChoiceDAO.create(mcqc);
        response.sendRedirect("welcome.jsp");
		}
}
