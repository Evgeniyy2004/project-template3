package project2;

import org.jetbrains.annotations.NotNull;
import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class Solver {
    public List<Point> solveByBFS(@NotNull Maze maze, Point start, Point end) {
        if (start.x >= maze.grid.length || end.x >= maze.grid.length) {
            throw new IllegalArgumentException();
        } else if (start.y >= maze.grid[0].length || end.y >= maze.grid[0].length) {
            throw new IllegalArgumentException();
        } else if (start.x < 0 || end.x  < 0) {
            throw new IllegalArgumentException();
        } else if (start.y < 0 || end.y  < 0) {
            throw new IllegalArgumentException();
        }
        if(start == end) {
            var res = new ArrayList<Point>(1);
            res.add(0, start);
            return res;
        }
        ArrayDeque<SinglyLinkedList> queue = new ArrayDeque<>();
        queue.addLast(new SinglyLinkedList(start, null));
        SinglyLinkedList res = null;
        HashMap <Point, Integer> paths = new HashMap<>();
        for (int i =0; i < maze.grid.length; i++) {
            for (int j = 0; j < maze.grid[0].length; j++) {
                paths.put(new Point(i, j), -1);
            }
        }
        paths.put(start, 0);
        while (!queue.isEmpty())
        {

            var current = queue.pop();
            if (current.curr.x == end.x && current.curr.y  == end.y) {
                res = new SinglyLinkedList(new Point(current.curr.x , current.curr.y ), current.previous);
                break;
            }
            for (int dx = -1; dx <= 1; dx++)
            {
                for (int dy = -1; dy <= 1; dy++)
                {
                    if (current.curr.y + dy < 0 ||
                        current.curr.y + dy >= maze.grid[0].length||
                        current.curr.x + dx < 0 ||
                        current.curr.x + dx >= maze.grid.length ||
                        (dy != 0 && dx != 0) ||
                        paths.get(new Point(current.curr.x + dx, current.curr.y + dy)) != -1) continue;
                    else
                    {


                        if (dx > 0) {
                            if (maze.grid[current.curr.x ][ current.curr.y].wallBottom) {
                                continue;
                            }
                        }
                        if (dx < 0) {
                            if (maze.grid[current.curr.x + dx][ current.curr.y + dy].wallBottom) {
                                continue;
                            }
                        }
                        if (dy > 0){
                            if (maze.grid[current.curr.x + dx][ current.curr.y + dy].wallLeft) {
                                continue;
                            }
                        }
                        if (dy < 0) {
                            if (maze.grid[current.curr.x][ current.curr.y].wallLeft) {
                                continue;
                            }
                        }

                        paths.put( new Point(current.curr.x + dx, current.curr.y + dy), paths.get(current.curr) + 1);
                        /*if (chests1.ContainsKey(new Point(current.curr.x + dx, current.curr.y + dy)))
                        {
                            //chests1.Remove(new Point(current.Value.X + dx, current.Value.Y + dy));
                            //yield return new SinglyLinkedList<Point>
                            (new Point(current.curr.x + dx, current.curr.y + dy),current);
                        }*/

                        queue.addLast(new SinglyLinkedList(new Point(current.curr.x + dx, current.curr.y + dy), current));
                    }
                }
            }
        }

        if (res == null) {
            return new ArrayList<>();
        } else {
            var now = res;
            Vector<Point> result = new Vector<>();
            while (now != null) {
                result.add(now.curr);
                now = now.previous;
            }
            return result.reversed();
        }
    }



    public List<Point> solveByDFS(@NotNull Maze maze, Point start, Point end) {
        if (start.x >= maze.grid.length || end.x >= maze.grid.length) {
            throw new IllegalArgumentException();
        } else if (start.y >= maze.grid[0].length || end.y >= maze.grid[0].length) {
            throw new IllegalArgumentException();
        } else if (start.x < 0 || end.x  < 0) {
            throw new IllegalArgumentException();
        } else if (start.y < 0 || end.y  < 0) {
            throw new IllegalArgumentException();
        }
        if(start == end) {
            var res = new ArrayList<Point>(1);
            res.add(0, start);
            return res;
        }
        Stack<SinglyLinkedList> stack = new Stack<>();
        stack.addLast(new SinglyLinkedList(start, null));
        SinglyLinkedList res = null;
        HashMap <Point, Integer> paths = new HashMap<>();
        for (int i =0; i < maze.grid.length; i++) {
            for (int j = 0; j < maze.grid[0].length; j++) {
                paths.put(new Point(i, j), -1);
            }
        }
        paths.put(start, 0);
        while (!stack.isEmpty())
        {

            var current = stack.pop();
            if (current.curr.x == end.x && current.curr.y  == end.y) {
                res = new SinglyLinkedList(new Point(current.curr.x , current.curr.y ), current.previous);
                break;
            }
            for (int dx = -1; dx <= 1; dx++)
            {
                for (int dy = -1; dy <= 1; dy++)
                {
                    if (current.curr.y + dy < 0 ||
                        current.curr.y + dy >= maze.grid[0].length||
                        current.curr.x + dx < 0 ||
                        current.curr.x + dx >= maze.grid.length ||
                        (dy != 0 && dx != 0) ||
                        paths.get(new Point(current.curr.x + dx, current.curr.y + dy)) != -1) continue;
                    else
                    {


                        if (dx > 0) {
                            if (maze.grid[current.curr.x ][ current.curr.y].wallBottom) {
                                continue;
                            }
                        }
                        if (dx < 0) {
                            if (maze.grid[current.curr.x + dx][ current.curr.y + dy].wallBottom) {
                                continue;
                            }
                        }
                        if (dy > 0){
                            if (maze.grid[current.curr.x + dx][ current.curr.y + dy].wallLeft) {
                                continue;
                            }
                        }
                        if (dy < 0) {
                            if (maze.grid[current.curr.x][ current.curr.y].wallLeft) {
                                continue;
                            }
                        }

                        paths.put( new Point(current.curr.x + dx, current.curr.y + dy), paths.get(current.curr) + 1);
                        /*if (chests1.ContainsKey(new Point(current.curr.x + dx, current.curr.y + dy)))
                        {
                            //chests1.Remove(new Point(current.Value.X + dx, current.Value.Y + dy));
                            //yield return new SinglyLinkedList<Point>
                            (new Point(current.curr.x + dx, current.curr.y + dy),current);
                        }*/

                        stack.push(new SinglyLinkedList(new Point(current.curr.x + dx, current.curr.y + dy), current));
                    }
                }
            }
        }

        if (res == null) {
            return new ArrayList<>();
        } else {
            var now = res;
            Vector<Point> result = new Vector<>();
            while (now != null) {
                result.add(now.curr);
                now = now.previous;
            }
            return result.reversed();
        }
    }
}

