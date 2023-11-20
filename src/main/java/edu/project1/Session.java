package edu.project1;

import org.jetbrains.annotations.NotNull;

class Session {

    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

     Session(String rightAnswer, char[] yourAnswer, int numberOfAttempts, int usedAttempts) {
        answer = rightAnswer;
        userAnswer = yourAnswer;
        maxAttempts = numberOfAttempts;
        attempts = usedAttempts;
    }

    @NotNull GuessResult guess(char guess) {
        if (answer.contains(String.valueOf(guess))) {
            char[] result = userAnswer;
            for (int i = 0; i < answer.length(); i++) {
                if (answer.charAt(i) == guess) {
                    result[i] = guess;
                }
            }
            return new GuessResult.SuccessfulGuess(result, attempts, maxAttempts, "Hit!");
        } else {
            attempts++;
            return new GuessResult.FailedGuess(userAnswer, attempts, maxAttempts);
        }
    }
}
