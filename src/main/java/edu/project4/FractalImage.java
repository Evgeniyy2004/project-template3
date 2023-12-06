package edu.project4;

import java.awt.geom.Point2D;
import java.util.HashMap;

public record FractalImage(Pixel[] data, int width, int height) {

    private static HashMap<Point2D,Pixel> allPixels = new HashMap<>();
    public static FractalImage create(int width, int height) {
        return new FractalImage(new Pixel[0],width, height);
    }
    boolean contains(double x, double y) {
        return (allPixels.get(new Point2D.Double(x,y)) != null);
    }
    Pixel pixel(int x, int y) {
        return allPixels.get(new Point2D.Double(x,y));
    }
}
