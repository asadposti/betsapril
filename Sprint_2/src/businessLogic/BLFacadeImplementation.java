package businessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import dataAccess.DataAccessImplementation;
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

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccessImplementation dbManager=new DataAccessImplementation(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
			}	
	}
	

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
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum, ArrayList<String> answers, ArrayList<Float> odds) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
	   DataAccess dBManager=new DataAccessImplementation();	    
		if(new Date().compareTo(event.getEventDate())>0) {
			dBManager.close();
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
		}	
		try {
			Question qry=dBManager.createQuestion(event,question,betMinimum, answers, odds);	
			dBManager.close();
			return qry;
		}
		catch(QuestionAlreadyExist q) {
			dBManager.close();
			throw new QuestionAlreadyExist(q.getMessage());
		}

   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
    	DataAccess dbManager=new DataAccessImplementation();
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		DataAccess dbManager=new DataAccessImplementation();
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
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
	@WebMethod
	public User registerUser(String iD, String password, String name, String surname, String email, String address, Gender g, String phone, 
			Nationality nat, String city, Date birthDate, String pic, boolean isAdmin) throws invalidID{
	
		DataAccess dBManager=new DataAccessImplementation();
	    User u = null;
	    try {
	    	u = dBManager.registerUser(iD, password, name, surname, email, address, g, phone, nat, city, birthDate, pic, isAdmin);
	    	dBManager.close();
	    	return u;
	    }
	    catch (invalidID i) {
		    dBManager.close();
			throw new invalidID(i.getMessage());
		}
	}
	
	/**
	 * This methods checks the validity of the credentials (id / password) inputed upon login.
	 * @param ID			ID of the presumed user.
	 * @param pw			password of the presumed user.
	 * 
	 * @return				int indicating privilege level of the user( 0: Regular user, 1: Admin, -1: Invalid credentials).
	 * @throws invalidID	exception thrown when no user entity with the input ID exists in the database.
	 */
	@WebMethod
	public User checkCredentials(String ID, String password) throws invalidID, invalidPW{
		DataAccess dBManager=new DataAccessImplementation();
	    try {
	    	User u = dBManager.checkCredentials(ID, password);
	    	dBManager.close();
	    	return u;
	    }	
	    catch (invalidID e) {
	    	dBManager.close();
			throw new invalidID(e.getMessage());
		}
	    catch (invalidPW e) {
	    	dBManager.close();
	    	throw new invalidPW(e.getMessage());
	    }
	}

	/**
	 * 
	 */
	@Override
	public List<User> searchByCriteria(String searchtext, int filter, boolean casesensitive) {
		DataAccess dbManager = new DataAccessImplementation();
		List<User> searchResult = dbManager.retrieveUsersByCriteria(searchtext, filter, casesensitive);
		dbManager.close();
		return searchResult;
	}
	
	/**
	 * 
	 */
	@Override
	public void removeUser(String ID) {
		DataAccess dbManager = new DataAccessImplementation();
		dbManager.removeUser(ID);
		dbManager.close();
	}
	
	/**
	 * 
	 */
	@Override
	public void updateUserInfo(String key, String ID, String name, String surname, String email, boolean isAdmin) throws invalidID{
		DataAccess dbManager = new DataAccessImplementation();
		try {	
			dbManager.updateUserInfo(key, ID,name,surname,email,isAdmin);
			dbManager.close();
		}
		catch(invalidID i) {
			dbManager.close();
			throw new invalidID();
		}
			
	}
	
	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
    	DataAccess dBManager=new DataAccessImplementation();
		dBManager.initializeDB();
		dBManager.close();
	}


	@Override 
	public void placeBet(Question q, User u, float amount, int answer) throws InsufficientCash{
		if(amount > u.getCash()) {
			throw new InsufficientCash();
		}
		else {
			DataAccessImplementation dbManager=new DataAccessImplementation();
			dbManager.placeBet(q, u, amount, answer);
			dbManager.close();
		}
		
	}










}

