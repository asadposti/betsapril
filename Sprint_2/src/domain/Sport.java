package domain;

public enum Sport {
	FOOTBALL("Football"),
	BASKETBALL("Basketball"),
	TENNIS("Tennis"),
	GOLF("Golf"),
	BOXING("Boxing"),
	HORSE_RACING("Horse racing");
	
	
	String asString;
	
	Sport(String name){
		asString = name;
	}
	
	public String getString() {
		return asString;
	}
	
	public static String[] namesArray() {
		Sport[] values = Sport.values();
		String[] nameArray = new String[values.length];
		for(Sport s : values) {
			nameArray[s.ordinal()] = s.asString;
		}
		return nameArray;
	}
	
}
