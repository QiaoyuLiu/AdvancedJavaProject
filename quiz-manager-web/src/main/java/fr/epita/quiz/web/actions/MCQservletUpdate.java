package fr.epita.quiz.web.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.MCQChoiceDAO;
import fr.epita.quiz.services.QuestionDAO;
import fr.epita.quiz.web.services.QuestionsServices;

public class MCQservletUpdate extends SpringServlet{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Inject
MCQChoiceDAO mcqChoiceDAO;
@Inject
QuestionDAO questionDAO;
@Inject
QuestionsServices qs;

@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	MCQChoice mcqc = qs.getMCQ(request,response);
	mcqChoiceDAO.update(mcqc);
	response.sendRedirect("welcome.jsp");
}
}