package edu.hw1;

public class Task7 {
    private Task7() {
        //not used
    }

    public static int rotateRight(int n, int shift) {
        int newshift = shift;
        int newN = n;
        if (newN < 0) {
            newN = Math.abs(newN);
        }
        if (newN == 0 || newN == 1 || newshift == 0) {
            return newN;
        }
        if (newshift < 0) {
            return rotateLeft(newN, Math.abs(newshift));
        }
        char[] start = Integer.toBinaryString(newN).toCharArray();
        char[] result = new char[start.length];
        newshift = newshift % start.length;
        for (int i = start.length - 1; i >= 0; i--) {
            if (i + newshift >= start.length) {
                result[i + newshift - start.length] = start[i];
            } else {
                result[i + newshift] = start[i];
            }
        }
        return Integer.parseInt(String.valueOf(result), 2);
    }

    public static int rotateLeft(int n, int shift) {
        int newShift = shift;
        int newN = n;
        if (newN < 0) {
            newN = Math.abs(newN);
        }
        if (newN == 0 || newN == 1 || newShift == 0) {
            return newN;
        }
        if (newShift < 0) {
            return rotateRight(newN, Math.abs(newShift));
        }
        char[] start = Integer.toBinaryString(newN).toCharArray();
        char[] result = new char[start.length];
        newShift = newShift % start.length;
        for (int i = start.length - 1; i >= 0; i--) {
            if (i - newShift < 0) {
                result[start.length + (i - newShift)] = start[i];
            } else {
                result[i - newShift] = start[i];
            }
        }
        return Integer.parseInt(String.valueOf(result), 2);
    }
}
