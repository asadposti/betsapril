package domain;

import gui.components.multibetOption;

public enum BetType {

	SINGLE("Single", 1),
	DOUBLE("Double", 2),
	TREBLE("Treble", 3),
	FOURFOLD("4-Fold", 4),
	FIVEFOLD("5-Fold", 5),
	SIXFOLD("6-Fold", 6),
	SEVENFOLD("7-Fold", 7),
	EIGHTFOLD("8-Fold", 8),
	NINEFOLD("9-Fold", 9),
	TENFOLD("10-Fold", 10),
	TRIXIE("Trixie", 4),
	PATENT("Patent" ,7),
	YANKEE("Yankee", 11),
	SUPER_YANKEE("Super Yankee", 26),
	HEINZ("Heinz", 120),
	SUPER_HEINZ("Super Heinz", 120),
	GOLIATH("Goliath", 247);


	private final String name;
	private final int value;

	BetType(String name, int value) {
		this.name = name;
		this.value = value;
	} 

	public String getString() {
		return name;
	}

	public int getValue() {
		return value;
	}

	/**
	 * Retrieves BetType attached to the given value.
	 */
	public static BetType getValue(int value) {
		for(BetType bt: BetType.values()) {
			if(bt.value == value) {
				return bt;
			}
		}
		return null;// not found
	}
}
