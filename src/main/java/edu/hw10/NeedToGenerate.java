package edu.hw10;

public class NeedToGenerate {

    public int a;
    private double b;
    private float c;
    private String d;

    public NeedToGenerate(@Min(value = 1000) @Max(value = 9000) int A, double B, float C,  String D) {
        a = A;
        b= B;
        c = C;
        d = D;
    }

    public NeedToGenerate create(@Min(value = Integer.MIN_VALUE) @Max(value = Integer.MAX_VALUE) int A, double B, float C,  String D) {
        return new NeedToGenerate(A,B,C,D);
    }
}
