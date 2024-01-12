package edu.project4;

public record FractalImage(Pixel[] data, int width, int height) {

    public static FractalImage create(int width, int height) {
        return new FractalImage(new Pixel[0], width, height);
    }
}
