package edu.hw9;

import java.awt.Point;
import java.io.File;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class Task3 {
    private static BlockingQueue<Point> nodesToReview = new LinkedBlockingDeque<>();
    private static  HashSet<Point> visited = new HashSet<>();
    private int x=-1;
    private int y=-1;

    private int endx=-1;
    private int endy=-1;
    public Task3(int x, int y, char[][] maze, int endx, int endy) {
        this.x = x;
        this.y = y;

    }

    public static void go() {
        Executor ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Task2 mw =  new Task2(new File("C:\\"));
        mw.run();
        for (int i = 0;i<Runtime.getRuntime().availableProcessors();i++) {
            ex.execute(new Task2());
        }
    }


    public void run() {
        if (x !=-1 && y !=-1) { //только для первого потока
            reviewFileSystem(x,y);
        } else {
            try {
                while (true) {


                    var now = nodesToReview.take();
                    reviewFileSystem(f);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    void reviewFileSystem(int x, int y) {
        if (f.isFile()) {

            //завершение обхода (+дополнительная логика)
            //System.out.println("Файл " + f.getName() + " найден потоком " + Thread.currentThread());
            return;
        }
        File[] files = f.listFiles();

        if (files.length > 1000) {

            for (int i = 0; i < files.length - 1; i++) {

                nodesToReview.add(files[i]); //добавление файлов всех кроме последнего
            }
            log.info( String.format("Директория %s найдена потоком %s",f,Thread.currentThread()));
            //последний дочерний узел используется для перехода дальше

            File last = files[files.length - 1];
            reviewFileSystem(last);

        }

    }
}
