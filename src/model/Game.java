package model;

import java.util.Set;
import java.util.TreeSet;

import app.GameStatus;
import io.HangmanViewPrinter;

public class Game {
	private WordsDatabase wordsDataBase = new WordsDatabase();
	private String originalPassword;
	private String hiddenPassword;
	private int userGuesses;
	private int userMistakes;
	private Set<Character> usedLetters = new TreeSet<>();

	public Game() {
		generatePassword();
		generateHiddenPassword();
	}

	private void generatePassword() {
		originalPassword = wordsDataBase.getRandomWord();
	}

	private void generateHiddenPassword() {
		StringBuilder passwordBuilder = new StringBuilder();
		for (int i = 0; i < originalPassword.length(); i++) {
			passwordBuilder.append("_");
		}
		hiddenPassword = passwordBuilder.toString();
	}

	private void updateHiddenPassword() {
		String updatedHiddenPassword = "";
		for (int i = 0; i < originalPassword.length(); i++) {
			if (usedLetters.contains(originalPassword.charAt(i))) {
				updatedHiddenPassword += originalPassword.charAt(i);
			} else {
				updatedHiddenPassword += "_";
			}
		}

		hiddenPassword = updatedHiddenPassword;
	}

	public void validate(char userGuess) {
		updateStatistics(userGuess);
		addToUsedLetters(userGuess);
		showUsedLetters();
		printHangmanView();
		updateHiddenPassword();
	}

	public void updateStatistics(char userGuess) {
		updateTries();
		updateMistakes(userGuess);
	}

	private void updateMistakes(char letter) {
		String userGuess = Character.toString(letter);
		if (!originalPassword.contains(userGuess) || usedLetters.contains(letter)) {
			userMistakes++;
		}
		System.out.println("Mistakes: " + userMistakes);
	}

	private void updateTries() {
		userGuesses++;
		System.out.println("Guesses: " + userGuesses);
	}

	private void addToUsedLetters(char userGuess) {
		usedLetters.add(userGuess);
	}

	private void showUsedLetters() {
		System.out.print("Letters in set: ");
		for (Character letter : usedLetters) {
			System.out.print(letter + ", ");
		}
		System.out.println("");
	}

	public void printHangmanView() {
		HangmanViewPrinter printer = new HangmanViewPrinter();
		printer.printView(userMistakes);

	}

	public void printPassword() {
		System.out.println("Generated password: " + this.originalPassword.toUpperCase());

	}

	public void printHiddenPassword() {
		System.out.println("Hidden password: " + this.hiddenPassword.toUpperCase());

	}

	public GameStatus updateGameStatus() {
		if (userMistakes == 10) {
			System.out.println("You lose!");
			return GameStatus.LOSE;
		} else if (hiddenPassword.equalsIgnoreCase(originalPassword)) {
			System.out.println("You win!");
			return GameStatus.WIN;
		}
		return GameStatus.IN_PROGRESS;
	}

}
