package edu.hw9;

import java.awt.Point;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task3 extends Thread {
    private static BlockingQueue<LinkedList<Point>> nodesToReview = new LinkedBlockingDeque<>();
    private static ConcurrentHashMap<Point, LinkedList> visited = new ConcurrentHashMap<>();

    public static LinkedList finalWay = null;
    private static int x = -1;
    private static int y = -1;
    private static Maze maze = null;
    private static int endx = -1;
    private static int endy = -1;

    public Task3(int x, int y, Maze maze, int endx, int endy) {
        Task3.x = x;
        Task3.y = y;
        Task3.maze = maze;
        Task3.endx = endx;
        Task3.endy = endy;
        var first = new LinkedList<Point>();
        first.add(new Point(x, y));
    }

    public Task3() {
    }

    public static void go() {
        var ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        var start = new LinkedList<Point>();
        start.add(new Point(x, y));
        nodesToReview.add(start);
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            ex.execute(new Task3());
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                var now = nodesToReview.take();
                if (!visited.contains(now.getLast())) {
                    addPoints(now.getLast().x, now.getLast().y, now);
                }
            }
        } catch (InterruptedException e) {
            log.info(e.toString());
        }
    }

    void addPoints(int x, int y, LinkedList<Point> currway) {
        if (x == endx && y == endy) {
            finalWay = currway;
            return;
        }
        visited.put(new Point(x, y), currway);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 && j != 0 || (i == 0 && j == 0)) {
                    continue;
                } else if (i + x < 0 || i + x >= maze.height) {
                    continue;
                } else if (j + y < 0 || j + y >= maze.width) {
                    continue;
                } else if (visited.containsKey(new Point(x + i, y + j))) {
                    continue;
                } else if (!checkMove(x, y, i, j, maze)) {
                    continue;
                }
                var t = new LinkedList<>(currway);
                t.add(new Point(x + i, y + j));
                nodesToReview.add(t);
            }
        }
    }

    boolean checkMove(int x, int y, int i, int j, Maze maze) {
        var result = true;
        if (i < 0 && maze.grid[x + i][y + j].wallBottom) {
            result = false;
        } else if (i > 0 && maze.grid[x][y].wallBottom) {
            result = false;

        } else if ((j > 0 && maze.grid[x + i][y + j].wallLeft) || (j < 0 || maze.grid[x][y].wallLeft)) {
            result = false;
        }
        return result;
    }
}
