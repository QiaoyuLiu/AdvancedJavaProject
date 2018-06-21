package fr.epita.quiz.web.actions;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.QuestionType;
import fr.epita.quiz.services.MCQChoiceDAO;
import fr.epita.quiz.services.QuestionDAO;
import fr.epita.quiz.services.QuestionOperationsService;
import fr.epita.quiz.web.services.QuestionsServices;

public class QuestionservletUpdate extends SpringServlet{
	/**
	 * 
	 */
	
	@Inject
	MCQChoiceDAO mcqChoiceDAO;
	@Inject
	QuestionDAO questionDAO;
	@Inject
	QuestionOperationsService qoService;
	@Inject
	QuestionsServices qs;
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    	Question question = qs.getQuestion(request,response);
        questionDAO.update(question);
        response.sendRedirect("welcome.jsp");
    }
    

    
}
