package edu.hw9;

import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task3 extends  Thread {
    private static BlockingQueue<LinkedList<Point>> nodesToReview = new LinkedBlockingDeque<>();
    private static  HashSet<Point> visited = new HashSet<>();

    private static LinkedList finalWay = null;
    private static int x=-1;
    private static int y=-1;
    private static  char [][] maze = new char[0][];
    private static int endx=-1;
    private static int endy=-1;
    public Task3(int x, int y, char[][] maze, int endx, int endy) {
        Task3.x = x;
        Task3.y = y;
        Task3.maze = maze;
        Task3.endx = endx;
        Task3.endy = endy;
        var first = new LinkedList<Point>();
        first.add(new Point(x,y));
    }

    public static void go() {
        Executor ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        //Task3 mw =  new Task3(new File("C:\\"));
        //mw.run();
        //for (int i = 0;i<Runtime.getRuntime().availableProcessors();i++) {
            //ex.execute(new Task2());
        //}
        for (int i = 0;i<Runtime.getRuntime().availableProcessors();i++)
        {
            ex.execute(new Task3(x,y, maze,endx,endy));
        }
    }

    @Override
    public void run() {
            try {
                while (true) {
                    var now = nodesToReview.take();
                    if ( !now.isEmpty() && !visited.contains(now.getLast())) {
                        reviewFileSystem(now.getLast().x, now.getLast().y, now);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    void reviewFileSystem(int x, int y, LinkedList<Point> currway) {
        if (x==endx && y == endy) {
            finalWay = currway;
            //завершение обхода (+дополнительная логика)
            //System.out.println("Файл " + f.getName() + " найден потоком " + Thread.currentThread());
            return;
        }
        //File[] files = f.listFiles();

        //if (files.length > 1000) {
        Vector<Point> all = new Vector<>();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j<=1; j++) {
                    if (i !=0 && j !=0) continue;
                    if (i +x <=0 || i + x >=maze.length) continue;
                    if (j +y <=0 || j + y >=maze[x].length) continue;
                    if (maze[x+i][y+j]=='#') continue;
                    if (visited.contains(new Point(x+i, y+j))) continue;
                    all.add(new Point(x+i, y+j));
                    visited.add(new Point(x+i, y+j));
                    //добавление файлов всех кроме последнего
                }
            }
        if (all.isEmpty()) return;
        for(int s = 0; s < all.size()-1; s++){
            var t = new LinkedList<>(currway);
            t.add(all.get(s));
            nodesToReview.add(t);
        }
            //log.info( String.format("Директория %s найдена потоком %s",f,Thread.currentThread()));
            //последний дочерний узел используется для перехода дальше

            //File last = files[files.length - 1];
            //reviewFileSystem(last);

        //}

    }
}
