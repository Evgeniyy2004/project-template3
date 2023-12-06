package edu.project4;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import static java.lang.Math.random;
import static java.util.Collections.rotate;
import java.awt.geom.AffineTransform;
//import static lombok.launch.PatchFixesHider.Transform.transform;

//@FunctionalInterface
public class Renderer {

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final int symmetry;

    public Renderer (int s) {
        this.symmetry = s;
    }

    FractalImage render(FractalImage canvas, Rect world, List<List<double>> variations, int samples, short iterPerSample, long seed)
        {
        for (int num = 0; num < samples; ++num) {
            var currx = ThreadLocalRandom.current().nextInt((int) world.x(), (int) (world.x()+world.width()));
            var curry = ThreadLocalRandom.current().nextInt((int)world.y(),(int)(world.y()+world.height()));
            Point2D pw = new Point2D.Double(currx, curry);

            for (short step = 0; step < iterPerSample; ++step) {
                var variation = variations.get(ThreadLocalRandom.current().nextInt(variations.size()));
                var x =

                double theta2 = 0.0;
                for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
                    var t = new AffineTransform(AffineTransform.getRotateInstance(theta2, 0,0));
                    var pwr = new Point2D.Double();
                    t.transform(pw,pwr);
                    //var pwr = AffineTransform.(pw, theta2);

                    if (!world.contains(pwr)) continue;

                    Pixel pixel = map_range(world, pwr, canvas);
                    if (pixel == null) continue;
                    pixel = new Pixel()
                    // 1. делаем лок на время работы с пикселем
                    // 2. подмешиваем цвет и увеличиваем hit count
                }
            }
        }
    }
}
