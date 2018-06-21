package fr.epita.quiz.web.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.QuestionType;
import fr.epita.quiz.services.MCQChoiceDAO;
import fr.epita.quiz.services.QuestionDAO;
import fr.epita.quiz.services.QuestionOperationsService;

public class QuestionsServices {
	@Inject
	MCQChoiceDAO mcqChoiceDAO;
	@Inject
	QuestionDAO questionDAO;
	@Inject
	QuestionOperationsService qoService;
	
	public MCQChoice getMCQ(HttpServletRequest request, HttpServletResponse response) {
	    MCQChoice mcqc=new MCQChoice();
		Question question=new Question();
		int b = Integer.parseInt(request.getParameter("getchoicevalide"));
		String choice = request.getParameter("getchoice");
		int mcqcid = Integer.parseInt(request.getParameter("getchoiceid"));
		int order = Integer.parseInt(request.getParameter("getchoiceorder"));
		int qid = Integer.parseInt((String) request.getSession().getAttribute("getselectquestionid"));
		question.setId(qid);
		question = questionDAO.search(question).get(0);
		mcqc.setQuestion(question);
		mcqc.setValid((b!=0));
		mcqc.setChoice(choice);
		mcqc.setId(mcqcid);
		mcqc.setOrder(order);
		return mcqc;
	}
	
	public MCQChoice getselectChoice(HttpServletRequest request, HttpServletResponse response) {
		MCQChoice mcqc = new MCQChoice();
		mcqc.setId(Integer.parseInt(request.getParameter("getselectchoiceid")));
		return mcqChoiceDAO.search(mcqc).get(0);
	}
	
	public Question getselectQuestion(HttpServletRequest request, HttpServletResponse response) {
		Question question = new Question();
		question.setId(Integer.parseInt(request.getParameter("getselectquestionid")));
		return questionDAO.search(question).get(0);
		}
	
	public void sendQuestionMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Question quest = new Question();
		quest.setId(Integer.parseInt(request.getParameter("getselectquestionid")));
		List<Question> questions = questionDAO.search(quest);
			MCQChoice mcq = new MCQChoice();
			mcq.setQuestion(questions.get(0));
			List<MCQChoice> choices = mcqChoiceDAO.search(mcq);
			for(int i=0;i<choices.size();i++) {
				session.setAttribute(String.valueOf(choices.get(i).getId()), choices.get(i).getChoice());
			}
	}
	
	public void sendAllQuestions(HttpServletRequest request, HttpServletResponse response)  {
		List<Question> questions = questionDAO.search(new Question());
		HttpSession session = request.getSession();
		int number = Integer.parseInt(request.getParameter("questionsnumber"));
		for(int i=0;i<questions.size();i++) {
			session.setAttribute(String.valueOf(questions.get(i).getId()), questions.get(i).getQuestion());
		}
		session.setAttribute("questionsnumber", number);
	}
	
	public void randomstart(HttpServletRequest request, HttpServletResponse response)  {
		int i = 0;
		int j = 0;
		int number;
		HttpSession session = request.getSession();
		number = Integer.parseInt(request.getParameter("questionsnumber"));
		List<Question> questions = qoService.randomquestions(number);
		for(i=0;i<questions.size();i++)
		{
			session.setAttribute("question"+1, questions.get(i).getQuestion());
			List<MCQChoice> choices = qoService.getChoices(questions.get(i), 4);
			for(j=0;j<choices.size();j++) {
			session.setAttribute("question"+i+"choice"+j+"title", choices.get(j).getChoice());
			session.setAttribute("question"+i+"choice"+j+"valid", choices.get(j).isValid());
			}
		}
		session.setAttribute("questionsnumber", number);

	}
	
    public Question getQuestion(HttpServletRequest request, HttpServletResponse response) {
		Question question=new Question();
		int qid = Integer.parseInt(request.getParameter("getquestionid"));
		String questionstring = request.getParameter("getquestion");
		question.setType(getQuestionType(request));
		question.setId(qid);
		question.setQuestion(questionstring);   	
		return question;
		}
    
    public QuestionType getQuestionType(HttpServletRequest request) {
    	String type = request.getParameter("getquestiontype");
    	switch (type) {
    	case "MCQ": return QuestionType.MCQ;
    	case "ASSOCIATIVE":return QuestionType.ASSOCIATIVE;
    	case "OPEN":return QuestionType.OPEN;
    	}
    	return null;
	}
   

}
