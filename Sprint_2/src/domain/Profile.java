package domain;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Profile {

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	private String ID;
	private String name;
	private String surname;
	private String email;
	private float cash;
	private String address;
	private Gender gender;
	private String phonenumber;
	private Nationality nationality;
	private String city;
	private Date birthdate;
	private String profilepic;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private User u;

	
	public Profile(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;

		this.cash = 50;         //placeholder value of 50 euros for testing purposes before credit cards etc are implemented.
	}
	
	//User with default profile pic
		public Profile(String iD, String nm, String srnm, String email, String addr, Gender g, String phn, Nationality nat,String city, Date birthdt) {
			super();
			this.ID = iD;
			this.name = nm;
			this.surname = srnm;
			this.email = email;
			this.address = addr;
			this.gender = g;
			this.phonenumber = phn;
			this.nationality = nat;
			this.city = city;
			this.birthdate = birthdt;
			this.profilepic = "images/smiley.png";
			this.cash = 50;         //placeholder value of 50 euros for testing purposes before credit cards etc are implemented.
		}	
		
		
		//User with custom profile pic already set
		public Profile(String iD, String nm, String srnm, String email, String addr, Gender g, String phn, Nationality nat,String city, Date birthdt, String pic) {
			super();
			this.ID = iD;
			this.name = nm;
			this.surname = srnm;
			this.email = email;
			this.address = addr;
			this.gender = g;
			this.phonenumber = phn;
			this.nationality = nat;
			this.city = city;
			this.birthdate = birthdt;
			this.profilepic = pic;
			this.cash = 50;         //placeholder value of 50 euros for testing purposes before credit cards etc are implemented.
		}
		

		public String getID() {
			return this.ID;
		}

		public String getProfilepic() {
			return profilepic;
		}


		public void setProfilepic(String profilepic) {
			this.profilepic = profilepic;
		}


		public void setCash(float cash) {
			this.cash = cash;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}



		public float getCash() {
			return cash;
		}
		

		public String getAddress() {
			return address;
		}


		public void setAddress(String address) {
			this.address = address;
		}


		public Gender getGender() {
			return gender;
		}


		public void setGender(Gender gender) {
			this.gender = gender;
		}


		public String getPhonenumber() {
			return phonenumber;
		}


		public void setPhonenumber(String phonenumber) {
			this.phonenumber = phonenumber;
		}


		public Nationality getNationality() {
			return nationality;
		}


		public void setNationality(Nationality nationality) {
			this.nationality = nationality;
		}


		public String getCity() {
			return city;
		}


		public void setCity(String city) {
			this.city = city;
		}


		public Date getBirthdate() {
			return birthdate;
		}


		public void setBirthdate(Date birthdate) {
			this.birthdate = birthdate;
		}

		
		
}
