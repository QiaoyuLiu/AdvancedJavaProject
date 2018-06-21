package fr.epita.quiz.web.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		int b = Integer.parseInt(request.getParameter("valide"));
		String choice = request.getParameter("choice");
		int mcqcid = Integer.parseInt(request.getParameter("id"));
		int order = Integer.parseInt(request.getParameter("order"));
		int qid = Integer.parseInt(request.getParameter("questionId"));
		question.setId(qid);
		question = questionDAO.search(question).get(0);
		mcqc.setQuestion(question);
		mcqc.setValid((b!=0));
		mcqc.setChoice(choice);
		mcqc.setId(mcqcid);
		mcqc.setOrder(order);
		return mcqc;
	}
	
	public void sendQuestionMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		Question quest = new Question();
		quest.setId(Integer.parseInt(request.getParameter("questionId")));
		List<Question> questions = questionDAO.search(quest);
			JsonObject Jquestion = new JsonObject();
			MCQChoice mcq = new MCQChoice();
			mcq.setQuestion(questions.get(0));
			List<MCQChoice> choices = mcqChoiceDAO.search(mcq);
			JsonArray Jchoices = new JsonArray();
			for(MCQChoice mcqc:choices) {
			JsonObject Jchoice = new JsonObject();
			Jchoice.addProperty("choice",mcqc.getChoice());
			Jchoice.addProperty("id",mcqc.getId());
			Jchoice.addProperty("valid",mcqc.isValid());
			Jchoice.addProperty("questionId",mcqc.getQuestion().getId());
			Jchoice.addProperty("order",mcqc.getOrder());
			Jchoices.add(Jchoice);
			}
			Jquestion.addProperty("questioncontent",questions.get(0).getQuestion());
			Jquestion.addProperty("questionId", questions.get(0).getId());
			Jquestion.add("choices",Jchoices);

		
		pw.println(Jquestion);
	}
	
	public JsonObject sendAllQuestions() throws IOException {
		JsonObject QandCs = new JsonObject();
		List<Question> questions = questionDAO.search(new Question());
		for(Question question:questions) {
			MCQChoice choice =new MCQChoice();
			choice.setQuestion(question);
			JsonObject Jquestion = new JsonObject();
			List<MCQChoice> choices = mcqChoiceDAO.search(choice);
			JsonArray Jchoices = new JsonArray();
			for(MCQChoice mcqc:choices) {
			JsonObject Jchoice = new JsonObject();
			Jchoice.addProperty("choice",mcqc.getChoice());
			Jchoice.addProperty("id",mcqc.getId());
			Jchoice.addProperty("valid",mcqc.isValid());
			Jchoice.addProperty("questionId",mcqc.getQuestion().getId());
			Jchoice.addProperty("order",mcqc.getOrder());
			Jchoices.add(Jchoice);
			}
			Jquestion.addProperty("questioncontent",question.getQuestion());
			Jquestion.addProperty("questionId", question.getId());
			Jquestion.add("choices",Jchoices);
			QandCs.add("question", Jquestion);
		}
		return QandCs;
	}
	
	public void randomstart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JsonObject QandCs = new JsonObject();
		PrintWriter pw = response.getWriter();
		List<Question> questions = qoService.randomquestions(Integer.parseInt(request.getParameter("questionsnumber")));
		for(Question question:questions) {
			JsonObject Jquestion = new JsonObject();
			List<MCQChoice> choices = qoService.getChoices(question, Integer.parseInt(request.getParameter("choicesnumber")));
			JsonArray Jchoices = new JsonArray();
			for(MCQChoice mcqc:choices) {
			JsonObject Jchoice = new JsonObject();
			Jchoice.addProperty("choice",mcqc.getChoice());
			Jchoice.addProperty("id",mcqc.getId());
			Jchoice.addProperty("valid",mcqc.isValid());
			Jchoice.addProperty("questionId",mcqc.getQuestion().getId());
			Jchoice.addProperty("order",mcqc.getOrder());
			Jchoices.add(Jchoice);
			}
			Jquestion.addProperty("questioncontent",question.getQuestion());
			Jquestion.addProperty("questionId", question.getId());
			Jquestion.add("choices",Jchoices);
			QandCs.add("question", Jquestion);
		}
		pw.println(QandCs.toString());
	}
	
    public Question getQuestion(HttpServletRequest request, HttpServletResponse response) {
		Question question=new Question();
		int qid = Integer.parseInt(request.getParameter("questionid"));
		String questionstring = request.getParameter("question");
		question.setType(getQuestionType(request));
		question.setId(qid);
		question.setQuestion(questionstring);   	
		return question;
		}
    
    public QuestionType getQuestionType(HttpServletRequest request) {
    	String type = request.getParameter("type");
    	switch (type) {
    	case "MCQ": return QuestionType.MCQ;
    	case "ASSOCIATIVE":return QuestionType.ASSOCIATIVE;
    	case "OPEN":return QuestionType.OPEN;
    	}
    	return null;
	}
   

}
