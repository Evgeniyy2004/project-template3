package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


class ConsoleHangman {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static Scanner SCANNER = new Scanner(System.in);
    private final int limitation;
    private final String word;

     ConsoleHangman(int attempts, String yourWord) {
        if (attempts < 2) {
            throw new IllegalArgumentException();
        }
        if (yourWord.length() < 2) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < yourWord.length(); i++) {
            if (!Character.isLetter(yourWord.charAt(i))) {
                throw new IllegalArgumentException();
            }
        }
        this.limitation = attempts;
        this.word = yourWord;
    }

    public void run() {
        boolean isfinished = false;
        String lostString = "You lost!";
        String currentString = "*".repeat(word.length());
        Session session = new Session(word, currentString.toCharArray(), limitation, 0);
        while (true) {
            if (isfinished) {
                break;
            }
            LOGGER.info("Guess a letter:");
            String key = SCANNER.nextLine();
            if (key.toLowerCase().equals("give up")) {
                LOGGER.info("The word: " + word);
                LOGGER.info(lostString);
                return;
            }
            if (key.length() != 1) {
                continue;
            } else {
                GuessResult result = tryGuess(session, key);
                currentString = new String(result.state());
                if (currentString.equals(word)) {
                    isfinished = true;
                }
                printState(result);
                if (result.attempt() == result.maxAttempts()) {
                    break;
                }
            }
        }

        if (isfinished) {
            LOGGER.info("You won!");
        } else {
            LOGGER.info(lostString);
        }
    }

    private GuessResult tryGuess(Session session, String input) {
        return session.guess(input.charAt(0));
    }

    private void printState(GuessResult guess) {
        LOGGER.info(guess.message());
        LOGGER.info("");
        LOGGER.info(new String(guess.state()));
    }
}
