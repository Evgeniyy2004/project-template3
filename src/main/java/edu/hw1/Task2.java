package edu.hw1;


public final class Task2 {

    private static final int RADIX_NUMBER = 10;

    private Task2() {
        //not used
    }

    public static int countDigits(int number) {
        int i = 1;
        int newNumber = number / RADIX_NUMBER;
        while (newNumber != 0) {
            newNumber = newNumber / RADIX_NUMBER;
            i += 1;
        }
        return i;
    }
}
