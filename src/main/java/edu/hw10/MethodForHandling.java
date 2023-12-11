package edu.hw10;

public class MethodForHandling {


    public long sum(int i) {
        if(i ==0) return 0;
        return i + sum(i-1);
    }

    public String sumOfStrings(String one, String two) {
        return one+two;
    }
}
