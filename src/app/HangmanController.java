package app;

import java.util.NoSuchElementException;
import io.DataReader;
import model.Game;
import model.Option;

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
		boolean isGameInProgres = true;
		while (isGameInProgres) {
			System.out.println("Your invisible password: " + hiddenPassword);
			char userGuess = readLetterFromUser();
			System.out.println("Your guess: " + userGuess);
			validate(game, userGuess);
			hiddenPassword = updatePassword(game);
			hangmanPrinter(game.getMistakes());

			if (mistakes == 10) {
				System.out.println("You lose!");
				isGameInProgres = false;
				break;
			}

			if (hiddenPassword.equalsIgnoreCase(game.getGeneratedWord())) {
				System.out.println("You win!");
				isGameInProgres = false;
				break;
			}
		}

		System.out.println("Generated password: " + game.getGeneratedWord().toUpperCase());

	}

	private String updatePassword(Game game) {
		return game.generateNewHidden();
	}

	private void validate(Game game, char userGuess) {
		game.validate(userGuess);
	}

	private char readLetterFromUser() {
		boolean isOk = false;
		char myChar = ' ';
		while (!isOk) {
			try {
				System.out.println("Type a letter: ");
				myChar = dataReader.readInput().charAt(0);
				isOk = true;
			} catch (StringIndexOutOfBoundsException e) {
				System.err.println("It's not a letter!");
			}
		}
		return myChar;
	}

	private String generateHiddenPassword(Game game) {
		return game.generateBasicHidden();
	}

	private void exit() {
		System.out.println("This is The End. Bye!");
		dataReader.close();
		System.exit(0);
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
