package edu.hw9;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;


@Slf4j
public class Task2 extends Thread {

    private static BlockingQueue<File> nodesToReview = new LinkedBlockingDeque<>();
    private File f;

    public Task2(File f) {
        this.f = f;
    }

    public Task2() {
    }

    public static void go() {
        Executor ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Task2 mw =  new Task2(new File("C:\\"));
        mw.run();
        for (int i = 0;i<Runtime.getRuntime().availableProcessors();i++) {
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
                e.printStackTrace();
            }

        }
    }

    void reviewFileSystem(File f) {
        if (f == null) {
            return;
        }
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

