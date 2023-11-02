package project2;

public class Cell{

    public Cell (int str, int row, boolean left, boolean right, boolean down, boolean up) {
        wallLeft = left;
        wallRight = right;
        wallBottom = down;
        wallUp = up;
        y = row;
        x = str;
    }

    public int x;
    public int y;
    public boolean wallBottom ;
    public  boolean wallLeft;
    public boolean wallRight;
    public boolean wallUp;

}
