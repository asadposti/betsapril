package businessLogic;

import java.util.Vector;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Question;
import domain.User;
import domain.Event;
import domain.Gender;
import domain.Nationality;
import exceptions.EventFinished;
import exceptions.InsufficientCash;
import exceptions.QuestionAlreadyExist;
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
	 * @return					the newly created User object.
	 * @throws invalidID		exception thrown when there is a pre existing user with this ID in the database.
	 */
	@WebMethod public User registerUser(String iD, String password, String name, String surname, String email, String address, Gender g, String phone, 
			Nationality nat, String city, Date birthDate, String pic, boolean isAdmin) throws invalidID;

	/**
	 * This methods checks the validity of the credentials (id / password) inputed upon login.
	 * @param ID			ID of the presumed user.
	 * @param pw			password of the presumed user.
	 * 
	 * @return				int indicating privilegde level of the user( 0: Regular user, 1:Admin, -1:Invalid credentials).
	 * @throws invalidID	exception thrown when no user entity with the input ID exists in the database.
	 */
	@WebMethod public User checkCredentials(String ID, String password) throws invalidID, invalidPW;
		
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
	@WebMethod public void placeBet(Question q, User u, float amount, int answer)throws InsufficientCash;
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();

	
}
