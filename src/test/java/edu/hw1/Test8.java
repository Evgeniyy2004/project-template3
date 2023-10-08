package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test8 {

    @Test
    @DisplayName("Кони не могут играть на доске размера не 8 x 8")
    void noBoardsTest() {
        //given
        int[][] notBoard1 = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
        };
        int[][] notBoard2 = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 1},
            {0, 0, 0, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
        };
        //then
        assertThat(Task8.knightBoardCapture(notBoard1)).isEqualTo(true);
        assertThat(Task8.knightBoardCapture(notBoard2)).isEqualTo(true);
    }



    @Test
    @DisplayName("Другие цифры не влияют на коней")
    void boardswithotherPiecesTest() {
        //given
        int[][] board = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 5, 1, 0, 1},
            {0, 0, 0, 3, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 2, 1, 0, 1, 0},
            {0, 0, 0, 0, 45, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        //then
        assertThat(Task8.knightBoardCapture(board)).isEqualTo(false);
    }

    @Test
    @DisplayName("Кони могут перемещаться по доске по г-образной траектории")
    void knightscanTest() {
        //given
        int[][] board1 = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
            };
        int[][] board2 = {
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 8, 0, 0},
                {1, 0, 0, 0, -4, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}
                };
        assertThat(Task8.knightBoardCapture(board1)).isEqualTo(true);
        assertThat(Task8.knightBoardCapture(board2)).isEqualTo(false);
    }
}
