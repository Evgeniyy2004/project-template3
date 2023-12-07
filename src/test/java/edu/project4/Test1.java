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
    void checkImages() throws IOException {
        BufferedImage bi = new
            BufferedImage(1980, 1020, BufferedImage.TYPE_INT_RGB);
        var g = bi.createGraphics();


        var toadd = new Vector<List<Double>>();
        for(int i = 0; i< 35; i++) {
            var now = ThreadLocalRandom.current().doubles(6,-1,1+1e-6);
            toadd.add(now.boxed().collect(Collectors.toList()));
        }

        List<Vector<Integer>> colors = new Vector<>();
        var color1 = new Vector<Integer>();
        color1.add(255);
        color1.add(255);
        color1.add(255);

        var color2 = new Vector<Integer>();
        color2.add(255);
        color2.add(0);
        color2.add(0);

        var color3 = new Vector<Integer>();
        color3.add(0);
        color3.add(255);
        color3.add(0);

        var color4 = new Vector<Integer>();
        color4.add(255);
        color4.add(0);
        color4.add(255);
        colors.add(color1);
        colors.add(color2);
        colors.add(color3);
        colors.add(color4);
        new Renderer().render(FractalImage.create(bi.getWidth(), bi.getHeight()),
            new Rect(0, 0, 1, 1), toadd,colors, 300000, 5000
        );
        Renderer.notEqual();
       for (Point p : Renderer.goals.keySet()) {
           var pixel = Renderer.goals.get(p);
           g.setColor(new Color(pixel[0],pixel[1],pixel[2]));
           g.drawLine(p.x,p.y,p.x,p.y);
       }


        if (Files.exists(new File("C:\\Users\\user\\IdeaProjects\\project-template3\\image.png").toPath())) {
            Files.delete(new File("C:\\Users\\user\\IdeaProjects\\project-template3\\image.png").toPath());
        }
        ImageIO.write(bi, "png", new File("C:\\Users\\user\\IdeaProjects\\project-template3\\image.png"));

    }
}
