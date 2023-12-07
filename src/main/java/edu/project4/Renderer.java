package edu.project4;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
//import static lombok.launch.PatchFixesHider.Transform.transform;

//@FunctionalInterface
public class Renderer {

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public static ConcurrentHashMap<Point, int[]> goals = new ConcurrentHashMap<>();
    private static final double ANTIACCURACY = 1e-6;

    FractalImage render(
        FractalImage canvas,
        Rect world,
        Vector<List<Double>> variations,
        List<Vector<Integer>> colors,
        int samples,
        int iterPerSample
    ) {
        var random2 = ThreadLocalRandom.current().nextInt(2 + 2 + 1);
        for (int num = 0; num < samples; num++) {
            var currx = ThreadLocalRandom.current().nextDouble(world.x(), (world.x() + world.width()) + ANTIACCURACY);
            var curry = ThreadLocalRandom.current().nextDouble(world.y(), (world.y() + world.height()) + ANTIACCURACY);
            for (int step = 0; step < iterPerSample; step++) {

                var random = ThreadLocalRandom.current().nextInt(variations.size());
                var variation = variations.get(random);
                var x = variation.get(0) * currx + variation.get(1) * curry + variation.get(2);
                var y = variation.get(2 + 1) * currx + variation.get(2 * 2) * curry + variation.get(2 * 2 + 1);
                var pw = new Point2D.Double();

                var random3 = ThreadLocalRandom.current().nextInt(colors.size());
                if (random2 == 0) {
                    pw.x = (x) / (x * x + y * y);
                    pw.y = (y) / (x * x + y * y);
                } else if (random2 == 1) {
                    pw.x = sin(x);
                    pw.y = sin(y);
                } else if (random2 == 2) {
                    pw.x = Math.atan2(y, x) / Math.PI;
                    pw.y = Math.sqrt(x * x + y * y) - 1;
                } else if (random2 == 2 + 1) {
                    pw.x = Math.sqrt(x * x + y * y) * sin(Math.sqrt(x * x + y * y) * Math.atan2(y, x));
                    pw.y = -Math.sqrt(x * x + y * y) * cos(Math.sqrt(x * x + y * y) * Math.atan2(y, x));
                } else {
                    pw.x = 1 / Math.PI * Math.atan2(y, x) * sin(Math.PI * Math.sqrt(x * x + y * y));
                    pw.y = 1 / Math.PI * Math.atan2(y, x) * cos(Math.PI * Math.sqrt(x * x + y * y));
                }

                if (!world.contains(pw)) {
                    continue;
                }
                if (step >= 0) {
                    if (goals.get(new Point(
                        ((int) (pw.x * canvas.width() / world.width())),
                        ((int) (pw.y * canvas.height() / world.height()))
                    )) == null) {
                        goals.put(
                            new Point(
                                Math.abs((int) (pw.x * canvas.width() / world.width())),
                                (int) (pw.y * canvas.height() / world.height())
                            ),
                            new int[] {colors.get(random3).get(0), colors.get(random3).get(1),
                                colors.get(random3).get(2), 1}
                        );
                    } else {
                        //Попали не в первый раз, считаем так:
                        var pixel = goals.get(new Point(
                            Math.abs((int) (pw.x * canvas.width() / world.width())),
                            (int) (pw.y * canvas.height() / world.height())
                        ));
                        goals.put(
                            new Point(
                                Math.abs((int) (pw.x * canvas.width() / world.width())),
                                Math.abs((int) (pw.y * canvas.height() / world.height()))
                            ),
                            new int[] {(pixel[0] + colors.get(random3).get(0)) / 2,
                                (pixel[1] + colors.get(random3).get(1)) / 2,
                                (pixel[2] + colors.get(random3).get(2)) / 2,
                                pixel[2 + 1] + 1}
                        );
                    }
                }

            }

        }
        return canvas;

    }

    public static void notEqual() {
        var max = 0.0;
        var gamma = (1 + 1.0 / (2.0 * 2.0 * 2.0 + 2.0)) * 2;
        for (Point p : goals.keySet()) {
            var normal = Math.log10(goals.get(p)[2 + 1]);
            if (normal > max) {
                max = normal;
            }
        }
        for (Point p : goals.keySet()) {
            var normal = Math.log10(goals.get(p)[2 + 1]) / max;
            goals.get(p)[0] = (int) (goals.get(p)[0] * Math.pow(normal, 1 / gamma));
            goals.get(p)[1] = (int) (goals.get(p)[1] * Math.pow(normal, 1 / gamma));
            goals.get(p)[2] = (int) (goals.get(p)[2] * Math.pow(normal, 1 / gamma));
        }

    }
}
