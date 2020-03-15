package dataAccess;


import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.ImageIcon;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Bet;
import domain.Event;
import domain.Gender;
import domain.Nationality;
import domain.Profile;
import domain.Question;
import domain.User;
import exceptions.EventFinished;
import exceptions.InsufficientCash;
import exceptions.QuestionAlreadyExist;
import exceptions.invalidID;
import exceptions.invalidPW;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccessImplementation implements DataAccess {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c;

	public DataAccessImplementation(boolean initializeMode)  {
		
		c=ConfigXML.getInstance();
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode)
			fileName=fileName+";drop";
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
	}

	public DataAccessImplementation()  {	
		 new DataAccessImplementation(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			
			Profile p1 = new Profile("aaa" , "Julen", "Urroz", "testemail@gmail.com", "testaddr", Gender.MALE, "+34688689414", Nationality.ES, "Zarautz", new Date(), "images/profilepic/palmera.png");
			Profile p2 = new Profile("bbb","JonAnder", "Beroz", "testemail2@gmail.com", "testaddr", Gender.MALE, "+346478646", Nationality.ES, "Hondarribi", new Date(), "images/profilepic/smiley.png");
			Profile p3 = new Profile("ccc","Asad", "Hayat", "testemail3@gmail.com","testaddr", Gender.MALE, "+347543734", Nationality.ES, "Donostia", new Date(), "images/profilepic/smiley.png");
			Profile p4 = new Profile("a","a", "a", "a@gmail.com","testaddr", Gender.MALE, "+111111111", Nationality.CI, "a", new Date(), "images/profilepic/smiley.png");	
			Profile p5 = new Profile("b","b", "b", "b@gmail.com","testaddr", Gender.FEMALE, "+222222222", Nationality.MA, "b", new Date(), "images/profilepic/smiley.png");
			Profile p6 = new Profile("Virus","Corona", "Virus","cvirus@gmail.com","testaddr", Gender.MALE, "+666666666", Nationality.ES, "London", new Date(), "images/profilepic/cvirus.png");
			Profile p7 = new Profile("Lemon","Yellow", "Lemon","ylemon@yahoo.es","testaddr", Gender.FEMALE, "+444444444", Nationality.ES, "Lima", new Date(), "images/profilepic/smiley.png");
			Profile p8 = new Profile("George11","George", "Washington","gwash@gg.gg","testaddr", Gender.MALE, "+7777777777", Nationality.ES, "Washington DC", new Date(), "images/profilepic/smiley.png");
			Profile p9 = new Profile ("Dog", "Im", "Dog", "doggie@gmail.com","testaddr", Gender.MALE, "+88888888", Nationality.ES, "Boston", new Date(), "images/profilepic/smiley.png");
			Profile p10 = new Profile ("Maria","Maria", "Ardilla", "marie123@gmail.com","testaddr", Gender.FEMALE, "+99999999", Nationality.AR, "Buenos Aires", new Date(), "images/profilepic/smiley.png");
			Profile p11 = new Profile ("xx", "xx", "xx", "xx@gmail.com","testaddr", Gender.MALE, "+25262662", Nationality.MX, "Donostia", new Date(), "images/profilepic/smiley.png");
			Profile p12 = new Profile ("ggwp", "gg", "wp", "ggwp@gmail.com","testaddr", Gender.FEMALE, "+364586838", Nationality.MA, "Cancun", new Date(), "images/profilepic/smiley.png");
			Profile p13 = new Profile ("hello","hello", "hello", "hello@gmail.com","testaddr", Gender.MALE, "+863121525", Nationality.JP, "Hello", new Date(), "images/profilepic/smiley.png");
			Profile p14 = new Profile ("r", "r", "r", "r@gmail.com","testaddr", Gender.FEMALE, "+347543734", Nationality.RU, "Donostia", new Date(), "images/profilepic/smiley.png");
			Profile p15 = new Profile ("Grandpa", "Grandpa", "Arnold", "arnold666@gmail.com","testaddr", Gender.MALE, "+2356754216", Nationality.GE, "Donostia", new Date(), "images/profilepic/smiley.png");
			Profile p16 = new Profile ("Antonio","Antonio", "Antonia", "Antoniojk@gmail.com","testaddr", Gender.MALE, "+624246264", Nationality.ES, "Donostia", new Date(), "images/profilepic/smiley.png");
			Profile p17 = new Profile ("Carl","Carl", "B", "carlb@gmail.com","testaddr", Gender.MALE, "+113552352", Nationality.US, "Donostia", new Date(), "images/profilepic/smiley.png");
			
			User u1 = new User("aaa", "bbb", true, p1);
			User u2 = new User("bbb", "bbb", true, p2);
			User u3 = new User("ccc", "bbb", true, p3);
			User u4 = new User("a", "a", false, p4);
			User u5 = new User("b", "b", false, p5);
			User u6 = new User("Virus", "aaaaaaaa", false, p6);
			User u7 = new User("Lemon", "12345678", false, p7);
			User u8 = new User("George11", "bbb", false, p8);
			User u9 = new User("Dog", "dddddddd", false, p9);
			User u10 = new User("Maria", "aaffgghh", false,p10);
			User u11 = new User("xx", "xx", false, p11);
			User u12 = new User("ggwp", "gggggggg", false,p12);
			User u13 = new User("hello", "hhhhhhh", false, p13);
			User u14 = new User("r", "rrrrrrr", false, p14);
			User u15 = new User("Grandpa", "bbbbbbbb", false,p15);
			User u16 = new User("Antonio", "bbb", false, p16);
			User u17 = new User("Carl", "bbb", false, p17);
			
			
			db.persist(u1);
			db.persist(u2);
			db.persist(u3);
			db.persist(u4);
			db.persist(u5);
			db.persist(u6);
			db.persist(u7);
			db.persist(u8);
			db.persist(u9);
			db.persist(u10);
			db.persist(u11);
			db.persist(u12);
			db.persist(u13);
			db.persist(u14);
			db.persist(u15);
			db.persist(u16);
			db.persist(u17);
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);
	
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			
			
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum, ArrayList<String> answers, ArrayList<Float> odds) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			q.setAnswer(answers);
			q.setOdds(odds);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){	 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){	 
		   res.add(d);
		  }
	 	return res;
	}
	
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
	public User registerUser(String iD, String password, String name, String surname, String email, String addr, Gender g, String phn, 
													Nationality nat,String city, Date birthdt, String pic, boolean isAdmin ) throws invalidID{
		
		if(db.find(User.class, iD) != null) {throw new invalidID("This ID is taken");}
		
		db.getTransaction().begin();
		Profile p = new Profile(iD, name, surname, email, addr, g, phn, nat, city, birthdt, pic);
		User u = new User(iD, password,isAdmin, p);
		db.persist(u);
		db.persist(p);
		db.getTransaction().commit();
		return u;
		
	}
	
	/**
	 * This methods checks the validity of the credentials (id / password) inputed upon login.
	 * @param ID			ID of the presumed user.
	 * @param pw			password of the presumed user.
	 * 
	 * @return				boolean indicating privilege level of the user( true:Admin, false:Regular user)
	 * @throws invalidID	exception thrown when no user entity with the input ID exists in the database.
	 */
	public User retrieveUser(String ID, String pw) throws invalidID, invalidPW {
		User u = db.find(User.class, ID);
		if(u == null) {
			throw new invalidID("ID does not correspond to a registered user");
		}
		else if(!u.getPassword().equals(pw)) {
			throw new invalidPW("Incorrect password");
		}
		else{

			return u;
		}
	}
	
	/**
	 * 
	 * @param iD
	 * @param password
	 * @param name
	 * @param surname
	 * @param email
	 * @return 
	 */
	public List<User> retrieveUsersByCriteria(String searchtext, int filter, boolean casesensitive) {
		List<User> searchResult = new ArrayList<User>();
		TypedQuery<User> query = null;
		if(searchtext.equals("")) {
			query = db.createQuery("SELECT u FROM User u", User.class);
		}
		else if(filter==0) {
			if(casesensitive) {
				query = db.createQuery("SELECT u FROM User u WHERE u.ID = \"" + searchtext +"\"", User.class);
			}
			else {
				query = db.createQuery("SELECT u FROM User u WHERE LOWER(u.ID) = \"" + searchtext.toLowerCase() +"\"", User.class);
			}
		}
		else if(filter== 1) {
			if(casesensitive) {
				query = db.createQuery("SELECT u FROM User u WHERE u.name = \"" + searchtext +"\"", User.class);
			}
			else {
				query = db.createQuery("SELECT u FROM User u WHERE LOWER(u.name) = \"" + searchtext.toLowerCase() +"\"", User.class);
			}

		}
		else if(filter== 2) {
			if(casesensitive) {
				query = db.createQuery("SELECT u FROM User u WHERE u.surname = \"" + searchtext + "\"", User.class);
			}
			else {
				query = db.createQuery("SELECT u FROM User u WHERE LOWER(u.surname) = \"" + searchtext.toLowerCase() +"\"", User.class);
			}
		}
		else if(filter==3) {
			if(casesensitive) {
				query = db.createQuery("SELECT u FROM User u WHERE u.email = \"" + searchtext + "\"", User.class);
			}
			else {
				query = db.createQuery("SELECT u FROM User u WHERE LOWER(u.email) = \"" + searchtext.toLowerCase() +"\"", User.class);
			}
		}
		searchResult = query.getResultList();
		return searchResult;
	}
	
	/**
	 * 
	 * @param iD
	 * @return
	 */
	public void removeUser(String iD) {
		db.getTransaction().begin();
		TypedQuery<User> query = db.createQuery("DELETE FROM User u WHERE u.ID = \"" + iD + "\"", User.class);
		query.executeUpdate();
		db.getTransaction().commit();
		System.out.println(iD + " has been deleted");
	}
	
	/**
	 * 
	 * @param iD
	 * @param name
	 * @param surname
	 * @param email
	 * @param isAdmin
	 */
	public void updateUserInfo(String key, String iD, String name, String surname, String email, String addr, Gender g,
										String phn, Nationality nat,String city, Date birthdt, boolean isAdmin) throws invalidID{
		
		User u = db.find(User.class, iD);
		//check if there is an existing user for the new ID
		if(!key.equals(iD)) {
			if(u != null) {
				throw new invalidID();
			}
			else {
				u = db.find(User.class, key);
				db.getTransaction().begin();
				TypedQuery<User> query = db.createQuery("DELETE FROM User u WHERE u.ID = \"" + key + "\"", User.class);
				query.executeUpdate();
				Profile p = new Profile(iD,name,surname,email, addr, g, phn, nat, city, birthdt);	
				User w = new User(iD,u.getPassword(),isAdmin,p);
				db.persist(w);
				db.getTransaction().commit();
			}
		}
		else {
			u = db.find(User.class, key);
			db.getTransaction().begin();
			u.getProfile().setName(name);
			u.getProfile().setSurname(surname);
			u.getProfile().setEmail(email);
			u.setAdmin(isAdmin);
			db.getTransaction().commit();
		}
		System.out.println(iD + " has been updated");
	}
	
	public void recordBet(Question q, String ID, float amount, int answer){
		User u = db.find(User.class, ID);
		db.getTransaction().begin();
		u.addBet(q, amount, answer);
		db.merge(u);
		db.getTransaction().commit();
		System.out.println("Bet placed sucessfully");
	}
	
	public void close(){
		db.close();
		emf.close();
		System.out.println("DataBase closed");
	}

	@Override
	public void updateUserInfo(String key, String iD, String name, String surname, String email, boolean isAdmin)
			throws invalidID {
		// TODO Auto-generated method stub
		
	}

	public float addCash(String ID, float addition) {
		User u = db.find(User.class, ID);
		db.getTransaction().begin();
		Profile p = u.getProfile();
		float updatedcash = p.getCash()+addition;
		p.setCash(updatedcash);
		db.getTransaction().commit();
		System.out.println("Cash added sucessfully");	
		return updatedcash;
	}
	
}
