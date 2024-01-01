package edu.hw10;

public class NeedToGenerate {

    public int a;
    private double b;
    private float c;
    private String d;

    public NeedToGenerate(@Min(value = 1000) @Max(value = 9000) int a, double b, float c, String d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public NeedToGenerate create(
        @Min(value = Integer.MIN_VALUE) @Max(value = Integer.MAX_VALUE) int a,
        double b,
        float c,
        String d
    ) {
        return new NeedToGenerate(a, b, c, d);
    }

}
