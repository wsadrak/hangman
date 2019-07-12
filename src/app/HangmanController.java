package app;

import java.util.NoSuchElementException;

import io.DataReader;
import model.Game;
import model.Option;
import model.WordsDatabase;

public class HangmanController {
	private DataReader dataReader = new DataReader();

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

		System.out.println("This is The End. Bye!");
	}

	private void printMenu() {
		System.out.println("Choose the option: ");
		for (Option option : Option.values()) {
			System.out.println(option);
		}
	}

	private Option getOption() {
		boolean optionOk = false;
		Option option = null;
		while (!optionOk) {
			try {
				option = Option.createFromInt(dataReader.getInt());
				optionOk = true;
			} catch (NoSuchElementException e) {
				System.err.println(e.getMessage() + "Please try again: ");
			}
		}
		return option;
	}

	private void play() {
		Game game = new Game();
		String hiddenPassword = generateHiddenPassword(game);
		int mistakes = game.getMistakes();
		while (isNextMoveAvailable(mistakes)) {
			System.out.println("Your invisible password: " + hiddenPassword);
			char userGuess = readLetterFromUser();
			System.out.println("Your guess: " + userGuess);
			validate(game, userGuess);
			hiddenPassword = updatePassword(game);
			hangmanPrinter(game.getMistakes());
		}
		
		if(mistakes < 10) {
			System.out.println("You win!");
		} else {
			System.out.println("You lose!");
		}
		
		System.out.println("Generated password: " + game.getGeneratedWord().toUpperCase());

	}

	private boolean isNextMoveAvailable(int mistakes) {
		return mistakes < 10;
	}

	private String updatePassword(Game game) {
		return game.generateNewHidden();
	}

	private void validate(Game game, char userGuess) {
		game.validate(userGuess);
	}

	private char readLetterFromUser() {
		System.out.println("Type a letter: ");
		String line = dataReader.readInput();
		char userGuess = line.charAt(0);
		return userGuess;
	}
	
	private String generateHiddenPassword(Game game){
		return game.generateBasicHidden();
	}

	private void exit() {
		dataReader.close();
	}
	
	public void hangmanPrinter(int mistakesCounter) {
		switch (mistakesCounter) {
		case 10:
			System.out.println(
					"\n  ================|\n //               |\n ||               |\n ||               |\n ||              _^_\n ||             / ..\\\n ||            [  _  ]\n ||             \\___/\n ||\n ||               ||    - *Snap!!!*\n ||              /||\\\n ||             //||\\\\\n ||            // || \\\\\n ||            *  ||  *\n ||              //\\\\\n ||             //  \\\\\n /\\            //    \\\\\n//\\\\         ***      ***\n/||\\\\\n_||_\\\\\n");
			break;
		case 9:
			System.out.println(
					"\n  ================|\n //               |\n ||               |\n ||               |\n ||              _^_\n ||             / ..\\\n ||            [  _  ]\n ||             \\___/\n ||               ||\n ||              /||\\\n ||             //||\\\\\n ||            // || \\\\\n ||            *  ||  *\n ||              //\n ||             //\n ||            //\n /\\          ***\n//\\\\ \n/||\\\\ \n_||_\\\\\n");
			break;
		case 8:
			System.out.println(
					"\n  ================|\n //               |\n ||               |\n ||               |\n ||              _^_\n ||             / ..\\\n ||            [  _  ]\n ||             \\___/\n ||               ||\n ||              /||\\\n ||             //||\\\\\n ||            // || \\\\\n ||            *  ||  *\n ||\n ||\n ||\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n");
			break;
		case 7:
			System.out.println(
					"\n  ================|\n //               |\n ||               |\n ||               |\n ||              _^_\n ||             / ..\\\n ||            [  _  ]\n ||             \\___/\n ||               ||\n ||              /||\n ||             //||\n ||            // ||\n ||            *  ||\n ||\n ||\n ||\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n");
			break;
		case 6:
			System.out.println(
					"\n  ================|\n //               |\n ||               |\n ||               |\n ||              _^_\n ||             / ..\\\n ||            [  _  ]\n ||             \\___/\n ||               ||\n ||               ||\n ||               ||\n ||               ||\n ||               ||\n ||\n ||\n ||\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n");
			break;
		case 5:
			System.out.println(
					"\n  ================|\n //               |\n ||               |\n ||               |\n ||              _^_\n ||             / ..\\\n ||            [  _  ]\n ||             \\___/\n ||               ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n");
			break;
		case 4:
			System.out.println(
					"\n  ================|\n //               |\n ||               |\n ||               |\n ||              _^_\n ||             / ..\\\n ||            [  _  ]\n ||             \\___/\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n");
			break;
		case 3:
			System.out.println(
					"\n  ================|\n //               |\n ||               |\n ||               |\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n");
			break;
		case 2:
			System.out.println(
					"\n  ================\n //\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n");
			break;
		case 1:
			System.out.println(
					"\n\n\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n");
			break;
		}
	}
}
