package edu.hw2;


public class Rectangle implements  Shape {

    double width;
    double height;

    @Override
    public void setParams(double a, double b) {
        this.height = a;
        this.width = b;
    }

    @Override
    public double area() {
        return width * height;
    }

}


