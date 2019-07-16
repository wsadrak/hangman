package app;

import java.util.NoSuchElementException;
import io.DataReader;
import model.Game;
import model.Option;

public class HangmanController {
	
	private DataReader dataReader = new DataReader();
	private Game game = new Game();
	private GameStatus gameStatus = GameStatus.IN_PROGRESS;
	
	public void mainLoop() {
		Option option;
		do {
			printMenu();
			option = getOption();

			switch (option) {
			case NEW_GAME:
				play();
				break;
			case EXIT:
				exit();
			}

		} while (option != Option.EXIT);

	}

	private void printMenu() {
		System.out.println("Choose the option: ");
		for (Option option : Option.values()) {
			System.out.println(option);
		}
	}

	private Option getOption() {
		boolean isOptionCorrect = false;
		Option option = null;
		while (!isOptionCorrect) {
			try {
				option = Option.createFromInt(dataReader.getInt());
				isOptionCorrect = true;
			} catch (NoSuchElementException e) {
				System.err.println(e.getMessage() + "Please try again: ");
			}
		}
		return option;
	}

	private void play() {
		while (gameStatus == GameStatus.IN_PROGRESS) {
			printHiddenPassword();
			char userGuess = readLetterFromUser();
			validateMove(userGuess);
			gameStatus = updateGameStatus();
		}
		printPassword();
	}

	private void printPassword() {
		game.printPassword();
	}

	private GameStatus updateGameStatus() {
		gameStatus = game.updateGameStatus();
		return gameStatus;
	}

	private void printHiddenPassword() {
		game.printHiddenPassword();
	}

	private void validateMove(char letter) {
		System.out.println("Your guess: " + letter);
		game.validate(letter);
	}

	private char readLetterFromUser() {
		boolean isLetterValid = false;
		char letter = ' ';
		while (!isLetterValid) {
			try {
				System.out.println("Type a letter: ");
				letter = dataReader.readInput().charAt(0);
				isLetterValid = true;
			} catch (StringIndexOutOfBoundsException e) {
				System.err.println("It's not a letter!");
			}
		}
		return letter;
	}

	private void exit() {
		System.out.println("This is The End. Bye!");
		dataReader.close();
		System.exit(0);
	}
	
	
}
