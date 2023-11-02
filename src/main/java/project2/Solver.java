package project2;

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
    List<Point> solveByBFS(Maze maze, Point start, Point end) {
        if(start == end) {
            var res = new ArrayList<Point>(1);
            res.add(0, start);
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
            for (int dx = -1; dx <= 1; dx++)
            {
                for (int dy = -1; dy <= 1; dy++)
                {
                    if (current.curr.y + dy < 0 ||
                        current.curr.y + dy >= maze.grid[0].length||
                        current.curr.x + dx < 0 ||
                        current.curr.x + dx >= maze.grid.length ||
                        dy != 0 && dx != 0 ||
                        paths.get(new Point(current.curr.x + dx, current.curr.y + dy)) != -1) continue;
                    else
                    {

                        paths.put( new Point(current.curr.x + dx, current.curr.y + dy), paths.get(current.curr) + 1);
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
                        /*if (chests1.ContainsKey(new Point(current.curr.x + dx, current.curr.y + dy)))
                        {
                            //chests1.Remove(new Point(current.Value.X + dx, current.Value.Y + dy));
                            //yield return new SinglyLinkedList<Point>
                            (new Point(current.curr.x + dx, current.curr.y + dy),current);
                        }*/
                        if (current.curr.x + dx == end.x && current.curr.y + dy == end.y) {
                            res = new SinglyLinkedList(new Point(current.curr.x + dx, current.curr.y + dy), current);
                            break;
                        }
                        queue.addLast(new SinglyLinkedList(new Point(current.curr.x + dx, current.curr.y + dy), current));
                    }
                }
            }
        }

        if (res == null) {
            return new ArrayList<>();
        } else {
            var now = res;
            int a = 1;
            Vector<Point> result = new Vector<>();
            while (now != null) {
                result.add(now.curr);
                now = now.previous;
            }
            return result.reversed();
        }
    }

    List<Point> solveByDFS(Maze maze, Point start, Point end) {
        if(start == end) {
            var res = new ArrayList<Point>(1);
            res.add(0, start);
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
            for (int dx = -1; dx <= 1; dx++)
            {
                for (int dy = -1; dy <= 1; dy++)
                {
                    if (current.curr.y + dy < 0 ||
                        current.curr.y + dy >= maze.grid[0].length||
                        current.curr.x + dx < 0 ||
                        current.curr.x + dx >= maze.grid.length ||
                        dy != 0 && dx != 0 ||
                        paths.get(new Point(current.curr.x + dx, current.curr.y + dy)) != -1) {
                        continue;
                    } else {

                        paths.put( new Point(current.curr.x + dx, current.curr.y + dy), paths.get(current.curr) + 1);
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

                        if (current.curr.x + dx == end.x && current.curr.y + dy == end.y) {
                            res = new SinglyLinkedList(new Point(current.curr.x + dx, current.curr.y + dy), current);
                            break;
                        }
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

