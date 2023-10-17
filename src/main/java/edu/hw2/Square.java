package edu.hw2;

public class Square  extends Rectangle implements Shape {
    double width;
    double height;

    @Override
    public void setParams(double a, double b) {
        this.height = Math.max(a, b);
        this.width = Math.max(a, b);
    }

    @Override
    public double area() {
        return width * height;
    }
}
