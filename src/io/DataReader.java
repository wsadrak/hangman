package io;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DataReader {
	private Scanner scanner = new Scanner(System.in);
	
	public int getInt() {
		try {
			return Integer.parseInt(readInput());
		} catch (NumberFormatException e) {
			throw new NoSuchElementException("It's not a number! ");
		} 
	}
	
	public String readInput() {
		return scanner.nextLine();
	}

	public void close() {
		scanner.close();
	}

}
