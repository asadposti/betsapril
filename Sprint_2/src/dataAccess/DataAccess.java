package dataAccess;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.TypedQuery;

import configuration.UtilDate;
import domain.Event;
import domain.Gender;
import domain.Nationality;
import domain.Question;
import domain.User;
import exceptions.QuestionAlreadyExist;
import exceptions.invalidID;
import exceptions.invalidPW;

public interface DataAccess {
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB();
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum, ArrayList<String> answers, ArrayList<Float> odds) throws  QuestionAlreadyExist;
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date);
	/**
	 * This method registers a new user in the database.
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
	public User registerUser(String iD, String password, String name, String surname, String email, String address, Gender g, String phone, 
													Nationality nat,String city, Date birthdDate, String pic, boolean isAdmin) throws invalidID;
	/**
	 * This methods checks the validity of the credentials (id / password) inputed upon login.
	 * @param ID			ID of the presumed user.
	 * @param pw			password of the presumed user.
	 * 
	 * @return				int indicating privilege level of the user( 0: Regular user, 1:Admin, -1:Invalid credentials)
	 * @throws invalidID	exception thrown when no user entity with the input ID exists in the database.
	 */
	public User checkCredentials(String ID, String pw) throws invalidID, invalidPW ;
	
	/**
	 * 
	 * @param iD
	 * @param password
	 * @param name
	 * @param surname
	 * @param email
	 * @return
	 */
	public List<User> retrieveUsersByCriteria(String searchtext, int filter, boolean casesensitive);
	
	/**
	 * 
	 * @param iD
	 * @return
	 */
	public void removeUser(String iD);
	
	/**
	 * 
	 * @param iD
	 * @param name
	 * @param surname
	 * @param email
	 * @param isAdmin
	 */
	public void updateUserInfo(String key, String iD, String name, String surname, String email, boolean isAdmin) throws invalidID;
	
	/**
	 * 
	 * @param q
	 * @param u
	 * @param amount
	 */
	public void placeBet(Question q, User u, float amount, int answer);
	
	public void close();

}
