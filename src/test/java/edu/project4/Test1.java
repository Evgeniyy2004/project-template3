package edu.project4;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.Test;

public class Test1 {
    @Test
    void oneThread() throws IOException {
        BufferedImage bi = new
            BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
        var g = bi.createGraphics();

        var toadd = new Vector<List<Double>>();
        for (int i = 0; i < 100; i++) {
            var now = ThreadLocalRandom.current().doubles(6, -1, 1 + 1e-6);
            toadd.add(now.boxed().collect(Collectors.toList()));
        }

        Vector<List<Integer>> colors = new Vector<>();
        for (int i = 0; i < 100; i++) {
            var now = ThreadLocalRandom.current().ints(3, 0, 256);
            colors.add(now.boxed().collect(Collectors.toList()));
        }
        new Renderer().render(FractalImage.create(bi.getWidth(), bi.getHeight()),
            new Rect(0, 0, 1, 1), toadd, colors, 30000, 1500
        );
        //Renderer.notEqual();
        for (Point p : Renderer.goals.keySet()) {
            var pixel = Renderer.goals.get(p);
            g.setColor(new Color(pixel[0], pixel[1], pixel[2]));
            g.drawLine(p.x, p.y, p.x, p.y);
        }

        if (Files.exists(new File("C:\\Users\\user\\IdeaProjects\\project-template3\\image.png").toPath())) {
            Files.delete(new File("C:\\Users\\user\\IdeaProjects\\project-template3\\image.png").toPath());
        }
        ImageIO.write(bi, "png", new File("C:\\Users\\user\\IdeaProjects\\project-template3\\image.png"));

    }
}
