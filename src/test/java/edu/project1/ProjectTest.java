package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectTest {
    @Test
    @DisplayName("Игра не будет запускаться, если количество попыток меньше двух или длина слова меньше двух")
    void incorrectInputTest(){
        try{
            var man = new ConsoleHangman(-1, "food");
            man.run();
            assertTrue(false);
        } catch (IllegalArgumentException exception) {
            assertTrue(true);
        }

        try{
            var man = new ConsoleHangman(3, "e");
            man.run();
            assertTrue(false);
        } catch (IllegalArgumentException exception) {
            assertTrue(true);
        }
    }

    @Test
    @DisplayName("В зависимости от того, содержится буква в слове или нет будет возвращен соответсвующий GuessResult")
    void correctConditionsTest(){
        Session session = new Session("giraffe","*".repeat("giraffe".length()).toCharArray(), 5, 0);
        var firstResult = session.guess('w');
        assertTrue(firstResult.getClass() == GuessResult.FailedGuess.class);
        var secondResult = session.guess('f');
        assertTrue(secondResult.getClass() == GuessResult.SuccessfulGuess.class);
        assertThat(secondResult.attempt()).isEqualTo(1);
        var thirdResult = session.guess('g');
        assertTrue(thirdResult.getClass() == GuessResult.SuccessfulGuess.class);
    }
}
