package edu.project4;

import java.awt.geom.Point2D;

public record Rect(double x, double y, double width, double height) {
    boolean contains(Point2D p) {
        return (p.getX() >= x && p.getX() <= x + width && p.getY() >= y && p.getY() <= y + height);
    }
}
