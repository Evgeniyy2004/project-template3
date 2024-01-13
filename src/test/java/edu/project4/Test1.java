package edu.project4;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

@Log
public class Test1 {
    @Test
    void oneThread() throws IOException {
        BufferedImage bi = new
            BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
        var g = bi.createGraphics();

        var toadd = new Vector<List<Double>>();
        for (int i = 0; i < 100; i++) {
            var now = ThreadLocalRandom.current().doubles(6, -1, 1 + 1e-6);
            var another = ThreadLocalRandom.current().ints(3,0,256).toArray();
            var res = now.boxed().collect(Collectors.toList());
            for(int j =0; j<3; j++) res.add((double) another[j]);
            toadd.add(res);
        }

        var t = new Renderer();
        t.render(FractalImage.create(bi.getWidth(), bi.getHeight()),
            new Rect(0, 0, 1, 1), toadd, 30000, 1500);
        t.notEqual();
        for (Point p : t.goals.keySet()) {
            var pixel = t.goals.get(p);
            g.setColor(new Color(pixel[0], pixel[1], pixel[2]));
            g.drawLine(p.x, p.y, p.x, p.y);
        }

        if (Files.exists(new File("C:\\Users\\user\\IdeaProjects\\project-template3\\image.png").toPath())) {
            Files.delete(new File("C:\\Users\\user\\IdeaProjects\\project-template3\\image.png").toPath());
        }
        ImageIO.write(bi, "png", new File("C:\\Users\\user\\IdeaProjects\\project-template3\\image.png"));

    }

    @Test
    void multiThread() throws IOException {
        //Arrange
        var start1 = System.currentTimeMillis();
        BufferedImage bi = new
            BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
        var g = bi.createGraphics();
        var toadd = new Vector<List<Double>>();
        for (int i = 0; i < 100; i++) {
            var now = ThreadLocalRandom.current().doubles(6, -1, 1 + 1e-6);
            var another = ThreadLocalRandom.current().ints(3,0,256).toArray();
            var res = now.boxed().collect(Collectors.toList());
            for(int j =0; j<3; j++) res.add((double) another[j]);
            toadd.add(res);
        }

        var pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        var t = new Renderer();
        CompletableFuture[] all = new CompletableFuture[Runtime.getRuntime().availableProcessors()];
        for(int y =0; y < Runtime.getRuntime().availableProcessors(); y++) {
            all[y] = CompletableFuture.runAsync(()-> t.render(FractalImage.create(bi.getWidth(), bi.getHeight()),
                new Rect(0, 0, 1, 1), toadd, 30000/ all.length, 1500),pool);
        }
        CompletableFuture.allOf(all).join();
        for (Point p : t.goals.keySet()) {
            var pixel = t.goals.get(p);
            g.setColor(new Color(pixel[0], pixel[1], pixel[2]));
            g.drawLine(p.x, p.y, p.x, p.y);
        }

       if (Files.exists(new File("C:\\Users\\user\\IdeaProjects\\project-template3\\image.png").toPath())) {
            Files.delete(new File("C:\\Users\\user\\IdeaProjects\\project-template3\\image.png").toPath());
        }
        ImageIO.write(bi, "png", new File("C:\\Users\\user\\IdeaProjects\\project-template3\\image.png"));

       var end1 = System.currentTimeMillis() - start1;
        var start = System.currentTimeMillis();
        oneThread();
        var end = System.currentTimeMillis() - start;

        log.info("Многопоточно:"+end1);
        log.info("Однопоточно:"+end);
    }
}
