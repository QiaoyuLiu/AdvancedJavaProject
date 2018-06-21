package fr.epita.quiz.web.actions;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epita.quiz.web.services.QuestionsServices;


public class JumptoChoiceServlet extends SpringServlet{
	@Inject
	QuestionsServices qs;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		qs.getMCQ(request, response);
		request.getSession().setAttribute(null, null);
			response.sendRedirect("choiceedit.jsp");

		
		}
		
	}