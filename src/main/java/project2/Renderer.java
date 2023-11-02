package project2;

import java.awt.Point;
import java.util.List;
import java.util.Map;

public class Renderer {
    public String render(Maze maze){
        StringBuilder result = new StringBuilder();
        for (int i =0; i < maze.grid[0].length; i++) result.append(" ＿");
        result.append("\n");
        for ( int r = 0; r < maze.grid.length; r++) {
            for (int i =0; i < maze.grid[0].length; i++) {
                if (maze.grid[r][i].wallLeft) {
                    result.append("|");
                } else result.append(" ");
                if (maze.grid[r][i].wallBottom) {
                    result.append("＿");
                } else result.append(" ");
            }
            result.append("|");
            result.append("\n");
        }
        return  result.toString();
    }
    public String render(Maze maze, List<Point> path) {
        StringBuilder result = new StringBuilder();
        for (int i =0; i < maze.grid[0].length; i++) result.append("＿");
        int curr = 0;
        for ( int r = 0; r < maze.grid.length; r++) {
            for (int i =0; i < maze.grid[0].length; i++) {
                if (maze.grid[r][i].wallLeft) {
                    result.append("|");
                } else result.append(" ");
                if (curr < path.size() && path.get(curr).x == r && path.get(curr).y == i) {
                    if (maze.grid[r][i].wallBottom) {
                        String utxt = String.join("\u0332", "0");
                        result.append(utxt);
                    } else result.append("0");
                    curr++;
                }
                else if (maze.grid[r][i].wallBottom) {
                    result.append("＿");
                } else result.append(" ");
            }
            result.append("|");
            result.append("\n");
        }
        return  result.toString();
    }
}
