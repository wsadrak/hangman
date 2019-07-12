package model;

import java.util.Set;
import java.util.TreeSet;

public class Game {
	private WordsDatabase wordsDataBase = new WordsDatabase();
	// zmienne
	private String generatedWord;
	public String getGeneratedWord() {
		return generatedWord;
	}

	private int guesses;
	private Set<Character> usedLetters = new TreeSet<>();
	private int mistakes;

	// konstruktory
	public Game(String guessWord) {
		this.generatedWord = guessWord;
	}
	
	public Game() {
		this.generatedWord = wordsDataBase.getRandomWord();
	}
	
	// metody
	public String generateBasicHidden() {
		StringBuilder hiddenPassword = new StringBuilder();
		for (int i = 0; i < generatedWord.length(); i++) {
			hiddenPassword.append("_");
		}
		return hiddenPassword.toString();
	}

	
	public String generateNewHidden() {
		String hidden = "";
		for (int i = 0; i < generatedWord.length(); i++) {
			if (usedLetters.contains(generatedWord.charAt(i))) {
				hidden += generatedWord.charAt(i);
			} else {
				hidden += "_";
			}
		}
		return hidden;
	}
	public void validate(char userGuess) {
		updateStatistics(userGuess);
		addToUsedLetters(userGuess);
		showUsedLetters();
	}

	public void updateStatistics(char userGuess) {
		updateTries();
		updateMistakes(userGuess);
		System.out.println("Guesses: " + guesses);
		System.out.println("Mistakes: " + getMistakes());
	}

	private void updateMistakes(char guess) {
		String userGuess = Character.toString(guess);
		if(!generatedWord.contains(userGuess) || usedLetters.contains(guess)) {
			setMistakes(getMistakes() + 1);
		}
	}
	
	private void updateTries() {
		guesses++;
	}
	private void addToUsedLetters(char userGuess) {
		usedLetters.add(userGuess);
	}

	private void showUsedLetters() {
		System.out.print("Letters in set: ");
		for (Character singleChar : usedLetters) {
			System.out.print(singleChar + ", ");
		}
		System.out.println("");
	}

	public int getMistakes() {
		return mistakes;
	}

	public void setMistakes(int mistakes) {
		this.mistakes = mistakes;
	}






	

	

}
