package edu.hw1;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

public final class Task5 {
    private Task5() {
        //not used
    }

    @SuppressWarnings("uncommentedmain")
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            System.out.println(isPalindromeDescendant(Integer.parseInt(str)));
        } catch (Exception e) {
            return;
        }
    }

    public static boolean isPalindromeDescendant(int arg) {
        if (arg < 0) {
            arg = Math.abs(arg);
        }
        String instr = Integer.toString(arg);
        if (instr.length() == 1) {
            return false;
        }
        if (instr.equals(new StringBuilder(instr).reverse().toString())) {
            return true;
        }
        if (Task2.countDigits(arg) % 2 != 0) {
            return false;
        }
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < instr.length() - 1; i += 2) {
            strb.append((instr.charAt(i) - '0') + (instr.charAt(i + 1) - '0'));
        }
        return isPalindromeDescendant(Integer.parseInt(strb.toString()));
    }
}
