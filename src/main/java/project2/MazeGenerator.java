package project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MazeGenerator implements   Generator {
    @Override
    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);

        for (int x = 0; x < maze.grid.length; x++)
        {
            for (int y = 0; y < maze.grid[0].length; y++)
            {
                maze.grid[x][y] = new Cell(x, y, true, false, true, false);
            }
        }

        for (int x = 0; x < maze.grid.length; x++)
        {
            //maze.grid[x][width - 1].wallRight = true;
        }

        for (int y = 0; y < maze.grid[0].length; y++)
        {
            //maze.grid[0][y].wallUp = true;
        }

        RemoveWallsWithBacktracker(maze.grid);


        return maze;
    }

    private void RemoveWallsWithBacktracker(Cell [][] maze)
    {
        Cell current = maze[0][0];
        int [][] isVisited = new int[maze.length][maze[0].length];
        for(int r =0; r < isVisited.length; r++)
        {
            for (int y = 0; y < isVisited[r].length; y++) {
                isVisited[r][y] = -1;
            }
        }
        isVisited[0][0] = 0;

        Stack<Cell> stack = new Stack<Cell>();
        do
        {
            List<Cell> unvisitedNeighbours = new ArrayList<Cell>();

            int x = current.x;
            int y = current.y;

            if (x > 0 && isVisited[x - 1][ y] == -1) unvisitedNeighbours.add(maze[x - 1][y]);
            if (y > 0 && isVisited[x][ y - 1] == -1) unvisitedNeighbours.add(maze[x] [y - 1]);
            if (x < maze.length - 2 && isVisited[x + 1][y] == -1) unvisitedNeighbours.add(maze[x + 1] [y]);
            if (y < maze[0].length - 1 && isVisited[x][y + 1] == -1) unvisitedNeighbours.add(maze[x][y + 1]);

            if (!unvisitedNeighbours.isEmpty())
            {
                Cell chosen = unvisitedNeighbours.get((int) Math.random()*unvisitedNeighbours.size());
                RemoveWall(current, chosen);

                isVisited[chosen.x][chosen.y] = isVisited[current.x][current.y] + 1;
                stack.push(chosen);
                current = chosen;
            }
            else
            {
                current = stack.pop();
            }
        } while (!stack.isEmpty());
    }

    private void RemoveWall(Cell a, Cell b)
    {
        if (a.x == b.x)
        {
            if (a.y > b.y) a.wallLeft = false;
            else b.wallLeft= false;
        }
        else
        {
            if (a.x > b.x) b.wallBottom  = false;
            else a.wallBottom = false;
        }
    }
}
