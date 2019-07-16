package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class WordsDatabase {
	private Random random = new Random();
	private ArrayList<String> database = new ArrayList<>();
	
	public WordsDatabase() {
		readDatabase();
	}
	
	public String getRandomWord() {
		int randomIndex = random.nextInt(database.size()+1);
		return database.get(randomIndex);
	}

	void readDatabase() {
		try (FileReader fileReader = new FileReader("dictionary.txt");
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				database.add(line);
			}

		} catch (FileNotFoundException e) {
			System.err.println("Can't found file!");
		} catch (IOException e) {
			System.err.println("Can't open file!");
		}
	}

}
