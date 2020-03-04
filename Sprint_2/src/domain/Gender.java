package domain;

public enum Gender {
	MALE("Male"), FEMALE("Female");
	
	private final String asString;
	
	Gender(String g){
		this.asString = g;
	}
	
	public String getString() {
		return asString;
	}
}
