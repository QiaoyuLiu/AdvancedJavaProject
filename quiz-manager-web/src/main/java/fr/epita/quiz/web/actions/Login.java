package fr.epita.quiz.web.actions;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.QuestionType;
import fr.epita.quiz.services.AuthenticationService;
import fr.epita.quiz.services.QuestionDAO;

/**
 * Servlet implementation class Login
 */


public class Login extends SpringServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	AuthenticationService auth;
	@Inject
	QuestionDAO questionDAO;
	/**
	 * Default constructor.
	 */
	public Login() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		final String login = request.getParameter("login");
		final String password = request.getParameter("password");
		System.out.println("login : " + login);
		System.out.println("password" + password);
		final boolean authenticated = true;
				//auth.authenticate(login, password);
		if (authenticated!=true)
		//request.getSession().setAttribute("authenticated", authenticated);
		//request.getSession().setAttribute("userName", login);

		response.sendRedirect("index.html");
		else response.sendRedirect("welcome.jsp");
	}

}
