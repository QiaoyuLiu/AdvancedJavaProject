/**
 * Ce fichier est la propriété de Thomas BROUSSARD
 * Code application :
 * Composant :
 */
package fr.epita.quiz.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;

/**
 * <h3>Description</h3>
 * <p>This class allows to ...</p>
 *
 * <h3>Usage</h3>
 * <p>This class should be used as follows:
 *   <pre><code>${type_name} instance = new ${type_name}();</code></pre>
 * </p>
 *
 * @since $${version}
 * @see See also $${link}
 * @author ${user}
 *
 * ${tags}
 */
public class QuestionOperationsService {

	@Inject
	private MCQChoiceDAO mcqChoicedao;

	@Inject
	private QuestionDAO questiondao;

	@Inject
	private SessionFactory factory;

	// @Transactional
	// TODO check that in a further lecture
	public void deleteQuestion(Question question) {
		final Transaction transaction = factory.openSession().beginTransaction();
		final MCQChoice criteria = new MCQChoice();
		criteria.setQuestion(question);
		final List<MCQChoice> choicesList = mcqChoicedao.search(criteria);
		for (final MCQChoice choice : choicesList) {
			mcqChoicedao.delete(choice);
		}
		questiondao.delete(question);
		transaction.commit();
	}
	
	//get random questions,if the questions number>the number we set then get number*questions,otherwise get all the questions
	public List<Question> randomquestions(int number) {
		Question question = new Question();
		List<Question> questionlist = questiondao.search(question);
		List<Question> resultlist = new ArrayList<Question>();
		HashSet<Integer> set = getrandom(questionlist.size(),(questionlist.size()>number)?number:questionlist.size());
		for(int i:set) {
			resultlist.add(questionlist.get(i));
		}
		return resultlist;
	}
	
	//get random choices,if the choices number>number we set then get number*questions,otherwise get them all
	public List<MCQChoice> getChoices(Question question,int number) {
		//get wrong answers
		MCQChoice choice = new MCQChoice();
		choice.setQuestion(question);
		choice.setValid(false);
		List<MCQChoice> result = new ArrayList<MCQChoice>();
		List<MCQChoice> choices = mcqChoicedao.search(choice);
		HashSet<Integer> set=getrandom(choices.size(),(choices.size()>number)?number:choices.size());
		for(int i:set) result.add(choices.get(i));
		//get the correct answer
		choice.setValid(true);
		result.add(mcqChoicedao.search(choice).get(0));
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//random number maker
	public HashSet<Integer> getrandom(int range,int number) {
		HashSet<Integer> set = new HashSet<Integer>();
		Random random = new Random();
		while(set.size()<number) {
			set.add(random.nextInt(range)+1);
		}
		return set;
		
	}

}
