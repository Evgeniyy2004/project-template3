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
            var currx = ThreadLocalRandom.current().nextDouble(world.x(), (world.x()+world.width())+1e-6);
            var curry = ThreadLocalRandom.current().nextDouble(world.y(),(world.y()+world.height())+1e-6);
            //Point2D pw = new Point2D.Double(currx, curry);

            for (short step = 0; step < iterPerSample; ++step) {
                var variation = variations.get(ThreadLocalRandom.current().nextInt(variations.size()));
                var x =variation.get(0)*currx+variation.get(1)*curry+variation.get(2);
                var y=variation.get(3)*currx+variation.get(4)*curry+variation.get(5);

                double theta2 = 0.0;
                for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
                    //var t = new AffineTransform(AffineTransform.getRotateInstance(theta2, 0,0));
                    var finalx = (x)
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
