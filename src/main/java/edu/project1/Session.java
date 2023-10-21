package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

class Session {

    private final static Logger LOGGER = LogManager.getLogger();
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

    public Session(String rightAnswer, char [] yourAnswer,int numberOfAttempts, int usedAttempts){
        answer = rightAnswer;
        userAnswer = yourAnswer;
        maxAttempts = numberOfAttempts;
        attempts = usedAttempts;
    }

    @NotNull GuessResult guess(char guess){
        if(answer.contains(String.valueOf(guess))) {
            char[] result = userAnswer;
            for (int i = 0; i < answer.length(); i++) {
                if (answer.charAt(i) == guess) {
                    result[i] = guess;
                }
            }
            return new GuessResult.SuccessfulGuess(result,attempts , maxAttempts,"Hit!");
        } else {
            attempts += 1;
            return new GuessResult.FailedGuess(userAnswer,attempts , maxAttempts);
        }
    };

    /*@NotNull GuessResult giveUp() {
        return new GuessResult.Defeat(userAnswer,attempts,maxAttempts, "You Lost!");
    };*/
}
