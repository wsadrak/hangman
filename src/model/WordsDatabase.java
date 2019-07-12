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
	
//	private final String[] words = { "chocolate", "hangman", "framework", "database", "collection" };

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
