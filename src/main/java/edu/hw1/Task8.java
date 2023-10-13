package edu.hw1;

import org.jetbrains.annotations.NotNull;

public class Task8 {

    private static final int BOARD_SIZE = 8;
    private static final int KNIGHT_LENGTH_MOVE = 3;

    private Task8() {
        //not used
    }

    @SuppressWarnings("uncommentedmain")
    public static boolean knightBoardCapture(int[] @NotNull [] chessboard) {
        if (chessboard.length != BOARD_SIZE) {
            return true;
        }
        for (int i = 0; i < chessboard.length; i++) {
            if (chessboard[i].length != BOARD_SIZE) {
                return true;
            }
        }
        for (int j = 0; j < chessboard.length; j++) {
            for (int k = 0; k < chessboard[j].length; k++) {
                if (chessboard[j][k] == 1) {
                    for (int x = (-1) * 2; x <= 2; x++) {
                        for (int y = 1; y <= 2; y++) {
                            if (y + j >= BOARD_SIZE || y + j < 0 || x + k >= BOARD_SIZE || x + k < 0) {
                                continue;
                            }
                            if (Math.abs(x) + Math.abs(y) != KNIGHT_LENGTH_MOVE) {
                                continue;
                            }
                            if (chessboard[j + y][k + x] == 1) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
