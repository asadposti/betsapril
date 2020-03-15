package domain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.swing.ImageIcon;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Stores credentials and additional information of registered users, including the active bets it has in place. 
 * Users may be regular or administrators (represented by isAdmin boolean).
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class User implements Comparable<User>{

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	private String ID;
	private String password;
	private boolean isAdmin;
	private Date registrationdate;
	private Date lastlogin;
	
	@OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
	private Profile profile;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	private ArrayList<Bet> bets;
	
	
	
	public ArrayList<Bet> getBets() {
		return bets;
	}


	public User(String iD, String password, boolean isAdmin, Profile p) {
		super();
		this.ID = iD;
		this.password = password;
		this.profile = p;
		this.isAdmin = isAdmin;
		this.bets = new ArrayList<Bet>();
		this.registrationdate = new Date();

	}


	public void setBets(ArrayList<Bet> bets) {
		this.bets = bets;
	}


	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}
	
	public void setProfile(Profile p) {
		this.profile = p;
	}

	public Date getRegistrationdate() {
		return registrationdate;
	}


	public void setRegistrationdate(Date registrationdate) {
		this.registrationdate = registrationdate;
	}


	public Date getLastlogin() {
		return lastlogin;
	}


	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	/**
	 * Registers the bet performed by a user
	 * @param q			the question the bet has been placed on.
	 * @param amount	the amount of money.
	 */
	public void addBet(Question q, float amount, int answer) {
		bets.add(new Bet(q,this,amount,answer));	
	}

	public void addBet(Bet b) {
		bets.add(b);
	}
	
	public String statusToString() {
		if(this.isAdmin) {
			return("Admin.");
		}
		else {
			return("User");
		}
	}

	/**
	 * Natural ordering is decided by the ID's
	 */
	public int compareTo(User u) {
		return this.ID.compareTo(u.ID);
	}
}
