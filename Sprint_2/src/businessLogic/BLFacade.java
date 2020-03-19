package businessLogic;

import java.util.Vector;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Question;
import domain.User;
import domain.Feedback.FeedbackType;
import domain.Bet;
import domain.Event;
import domain.Gender;
import domain.Nationality;
import domain.Profile;
import exceptions.EventFinished;
import exceptions.InsufficientCash;
import exceptions.NoAnswers;
import exceptions.QuestionAlreadyExist;
import exceptions.QuestionNotFound;
import exceptions.invalidID;
import exceptions.invalidPW;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod Question createQuestion(Event event, String question, float betMinimum, ArrayList<String> answers, ArrayList<Float> odds) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method registers a new user.
	 * 
	 * @param iD				ID of the new user.
	 * @param password			password of the new user.
	 * @param name				name of the new user.
	 * @param surname			surname of the new user.
	 * @param email				email of the new user.
	 * @param isAdmin			whether this user has admin. privileges or not.
	 * 
	 * @throws invalidID		exception thrown when there is a pre existing user with this ID in the database.
	 */
	@WebMethod public void registerUser(String iD, String password, String name, String surname, String email, String address, Gender g, String phone, 
			Nationality nat, String city, Date birthDate, String pic, boolean isAdmin) throws invalidID;

	/**
	 * This methods checks the validity of the credentials (id / password) inputed upon login.
	 * @param ID			ID of the presumed user.
	 * @param pw			password of the presumed user.
	 * 
	 * @return				boolean indicating privilege level of the user( true: Admin, false:Regular user).
	 * @throws invalidID	exception thrown when no user entity with the input ID exists in the database.
	 */
	@WebMethod public boolean checkCredentials(String ID, String password) throws invalidID, invalidPW;
		
	/**
	 * 
	 * @param iD
	 * @param nam
	 * @param surn
	 * @param Email
	 * @return
	 */ 
	@WebMethod public List<User> searchByCriteria(String searchtext, int filter, boolean casesensitive);
	
	/**
	 * 
	 * @param searchtext
	 * @param filter
	 * @return
	 */
	@WebMethod public void removeUser(String ID);
	
	/**
	 * 
	 * @param searchtext
	 * @param filter
	 * @return
	 */
	@WebMethod public void updateUserInfo(String key, String ID,String name,String surname, String email,boolean isAdmin) throws invalidID;
	
	/**
	 * 
	 * @param q
	 * @param amount
	 */
	@WebMethod public void placeBet(Question q, String ID, float amount, int answer)throws InsufficientCash;
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();

	/**
	 * This method checks if a user is currently logged in
	 * @return    boolean(true: if a user is logged in, false: else)
	 */
	public boolean isLoggedIn();
	
	/**
	 * Logs the current user out by setting the attributes related to the current session to null
	 */
	public void logOut();
	
	/**
	 * Retrieves the bets the currently logged suer has in place
	 * @return		List<Bet> user's bets
	 */
	public List<Bet> retrieveBets();
	
	/**
	 * Retrieves the profile of the currently logged user
	 * @return	Profile object containing information about the user
	 */
	public Profile getProfile();
	
	/**
	 * Indicates if the logged user has an admin status.
	 * @return	boolean(true: if loggeduser is an admin, false:else)
	 */
	public boolean isAdmin();
	
	/**
	 * Adds introduced amount the cash stored on the user's account
	 * @param amount	amount of money to add(float)
	 * @return	cash on the account after the addition
	 */
	public float addCash(float amount);
	
	public void submitFeedback(FeedbackType fbtype, String email, String name, String summary, String details, File file);


	ArrayList<String> getQuestionAnswers(int questionId) throws QuestionNotFound, NoAnswers;


	ArrayList<Float> getOdds(int questionId) throws QuestionNotFound, NoAnswers;
	
}
