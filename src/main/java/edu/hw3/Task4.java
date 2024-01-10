package edu.hw3;

import java.util.TreeMap;
import static java.lang.Math.pow;

public class Task4 {

    private Task4() {

    }

    private final static TreeMap<Integer, String> MAP = new TreeMap<Integer, String>();

    static {
        MAP.put((int) pow(2 * 2 * 2 + 2, 2 + 1), "M");
        MAP.put((int) pow(2 * 2 * 2 + 2, 2 + 1) - (int) pow(2 * 2 * 2 + 2, 2), "CM");
        MAP.put((int) pow(2 * 2 * 2 + 2, 2 + 1) / 2, "D");
        MAP.put((int) pow(2 * 2 * 2 * 2 + 2 * 2, 2), "CD");
        MAP.put((int) pow(2 * 2 * 2 + 2, 2), "C");
        MAP.put((int) pow(2 * 2 * 2 + 2, 2) - (2 * 2 * 2 + 2), "XC");
        MAP.put((int) pow(2 * 2 + (2 + 1), 2) + 1, "L");
        MAP.put((int) pow(2 * 2 + 2, 2) + 2 * 2, "XL");
        MAP.put((int) pow(2 + 1, 2) + 1, "X");
        MAP.put((int) pow(2 + 1, 2), "IX");
        MAP.put(2 * 2 + 1, "V");
        MAP.put(2 * 2, "IV");
        MAP.put(1, "I");
    }

    public static String convertToRoman(int input) {
        if (input <= 0) {
            throw new IllegalArgumentException("В системе римских цифр есть только положительные числа");
        }
        if (MAP.containsKey(input))  {
            return MAP.get(input);
        } else {
            return MAP.get(MAP.floorKey(input)) + convertToRoman(input - MAP.floorKey(input));
        }

    }
}
