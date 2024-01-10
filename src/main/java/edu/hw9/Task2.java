package edu.hw9;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task2 extends Thread {

    private static BlockingQueue<File> nodesToReview = new LinkedBlockingDeque<>();

    private static final int NUMBER = 10;
    private File f;

    public Task2(File f) {
        this.f = f;
    }

    public Task2() {
    }

    public static void go() {
        Executor ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Task2 mw = new Task2(new File("C:\\"));
        mw.run();
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            ex.execute(new Task2());
        }
    }

    @Override
    public void run() {
        if (f != null) { //только для первого потока
            reviewFileSystem(f);
        } else {
            try {
                while (true) {
                    f = nodesToReview.take();
                    reviewFileSystem(f);
                }
            } catch (InterruptedException e) {
               log.info(e.toString());
            }

        }
    }

    void reviewFileSystem(File f) {
        if (f == null || !f.isDirectory()) {
            return;
        }
        File[] files = f.listFiles();
        if (Files.isReadable(f.toPath())
            || Files.isWritable(f.toPath())) {
            if (files.length > NUMBER) {

                log.info(String.format(
                    "Директория %s найдена потоком %s, количество файлов - %d",
                    f,
                    Thread.currentThread(),
                    files.length
                ));
                //последний дочерний узел используется для перехода дальше
            }
            if (files.length > 0) {
                for (int i = 0; i < files.length - 1; i++) {

                    nodesToReview.add(files[i]); //добавление файлов всех кроме последнего
                }
                File last = files[files.length - 1];
                reviewFileSystem(last);
            }
        }

    }

}

