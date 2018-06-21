package fr.epita.quiz.web.actions;

import java.io.IOException;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.epita.quiz.web.services.QuestionsServices;

public class StartServlet extends SpringServlet{

	@Inject
	QuestionsServices qs;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
			

		qs.randomstart(request, response);
		request.getSession().getAttribute("1");
		response.sendRedirect("quiz.html");
		}
		
	}



