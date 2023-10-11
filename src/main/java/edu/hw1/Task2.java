package edu.hw1;


public final class Task2 {
    private Task2() {
        //not used
    }

    public static int countDigits(int number) {
        int i = 1;
        int newNumber = number / 10;
        while (newNumber != 0) {
            newNumber = newNumber / 10;
            i += 1;
        }
        return i;
    }
}
