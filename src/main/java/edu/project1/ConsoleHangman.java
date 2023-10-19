package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

class ConsoleHangman {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static Scanner SCANNER = new Scanner(System.in);
    private final int limitation;
    private final String word;

    public ConsoleHangman(int attempts, String yourWord)
    {
        this.limitation = attempts;
        this.word = yourWord;
    }
    public void run() {

        int i = 0;
        boolean isfinished = false;
        String currentString = "*".repeat(word.length());
        while (i < limitation || !isfinished) {
            LOGGER.info("Guess a letter:");
            String key = SCANNER.next();
            if (key.toLowerCase().equals("give up")) {
                LOGGER.info("The word: " +word);
                LOGGER.info("You lost!");
                return;
            }
            if (key.length() != 1) {
                continue;
            } else {
                Session session = new Session(word, currentString.toCharArray(), i, limitation);
                GuessResult result = tryGuess(session, key);
                currentString = new String(result.state());
                if(currentString.equals(word)){
                    isfinished = true;
                }
                printState(result);
                i++;
            }
        }
    }

    private GuessResult tryGuess(Session session, String input) {
        return session.guess(input.charAt(0));
    }

    private void printState(GuessResult guess)
    {
        LOGGER.info(guess.message());
        LOGGER.info("");
        LOGGER.info(new String(guess.state()));
    }
}
