package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

sealed interface GuessResult {

    final static Logger LOGGER = LogManager.getLogger();
    char[] state();
    int attempt();
    int maxAttempts();
    @NotNull String message();

    /*record Defeat(char [] state, int attempt, int maxAttempts, String message) implements GuessResult {
        @Override
        public char[] state() {
            return state;
        }

        @Override
        public int attempt() {
            return attempt;
        }

        @Override
        public int maxAttempts() {
            return maxAttempts;
        }

        @Override
        public @NotNull String message() {
            return message;
        }
    }*/
    /*record Win(char [] state, int attempt, int maxAttempts, String message) implements GuessResult {
        @Override
        public char[] state() {
            return state;
        }

        @Override
        public int attempt() {
            return attempt;
        }

        @Override
        public int maxAttempts() {
            return maxAttempts;
        }

        @Override
        public @NotNull String message() {
            return message;
        }


    }*/
    record SuccessfulGuess(char [] state, int attempt, int maxAttempts, String message) implements GuessResult {
        @Override
        public char[] state() {
            return state;
        }

        @Override
        public int attempt() {
            return attempt;
        }

        @Override
        public int maxAttempts() {
            return maxAttempts;
        }

        @Override
        public @NotNull String message() {
            return message;
        }
    }
    record FailedGuess(char [] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public char[] state() {
            return state;
        }

        @Override
        public int attempt() {
            return attempt;
        }

        @Override
        public int maxAttempts() {
            return maxAttempts;
        }

        @Override
        public @NotNull String message() {
            return  String.format("Missed, mistake %s out of %s",attempt,maxAttempts);
        }
    }
}
