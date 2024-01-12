package anotherproject;

import java.awt.Point;
import java.util.List;

public class Renderer {
    public String render(Maze maze) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maze.grid[0].length; i++) {
            result.append(" _");
        }
        result.append("\n");
        for (int r = 0; r < maze.grid.length; r++) {
            for (int i = 0; i < maze.grid[0].length; i++) {
                if (maze.grid[r][i].wallLeft) {
                    result.append("|");
                } else {
                    result.append(" ");
                }
                if (maze.grid[r][i].wallBottom) {
                     result.append("_");
                } else {
                    result.append(" ");
                }
            }
            result.append("|");
            result.append("\n");
        }
        return  result.toString();
    }

    public String render(Maze maze, List<Point> path) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maze.grid[0].length; i++) {
            result.append(" _");
        }
        result.append("\n");
        for (int r = 0; r < maze.grid.length; r++) {
            for (int i = 0; i < maze.grid[0].length; i++) {
                if (maze.grid[r][i].wallLeft) {
                    result.append("|");
                } else {
                    result.append(" ");
                }
                if (path.contains(new Point(r, i))) {
                    result.append("0");
                } else if (maze.grid[r][i].wallBottom) {
                    result.append("_");
                } else {
                    result.append(" ");
                }
            }
            result.append("|");
            result.append("\n");
        }
        return  result.toString();
    }
}
