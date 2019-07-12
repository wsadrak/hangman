package model;
import java.util.NoSuchElementException;


public enum Option {
	EXIT(0, "EXIT"), NEW_GAME(1, "NEW GAME");

	private int value;
	private String description;

	private Option(int value, String description) {
		this.value = value;
		this.description = description;
	}

	public static Option createFromInt(int option) throws NoSuchElementException {
		try {
			return Option.values()[option];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchElementException("Option with ID " + option + " doesn't exist. ");
		}
	}
	
	@Override
	public String toString() {
		return "[" + value  + "] - " + description;
	}
}
