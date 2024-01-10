package edu.hw9;

public final class Maze {
    public final int height;
    public final int width;
    public Cell[][] grid;

    public Maze(int one, int two) {
        if (one <= 0 || two <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new Cell[one][two];
        height = one;
        width = two;
    }

}
