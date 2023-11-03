package project2;

public final class Maze {
    private final int height;
    private final int width;
    public Cell[][] grid;

    public Maze (int one, int two) {
        if(one <= 0 || two <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new Cell[one][two];
        height = one;
        width = two;
    }

}
