package edu.project1;

import java.util.Scanner;
import lombok.extern.java.Log;

@Log
public class ConsoleHangman {

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
            log.info("Guess a letter:");
            String key = SCANNER.nextLine();
            if (key.toLowerCase().equals("give up")) {
                log.info("The word: " + word);
                log.info(lostString);
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
            log.info("You won!");
        } else {
            log.info(lostString);
        }
    }

    private GuessResult tryGuess(Session session, String input) {
        return session.guess(input.charAt(0));
    }

    private void printState(GuessResult guess) {
        log.info(guess.message());
        log.info("");
        log.info(new String(guess.state()));
    }
}
