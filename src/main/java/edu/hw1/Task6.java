package edu.hw1;

import org.jetbrains.annotations.NotNull;
import java.util.Arrays;

public final class Task6 {
    private Task6() {
        //not used
    }

    public static int countK(int arg) {
        int newArg = arg;
        if (newArg < 0) {
            newArg = Math.abs(newArg);
        }
        String str = Integer.toString(newArg);
        if (str.length() != 4 || str.equals("1000")) {
            return -1;
        }
        if (str.charAt(0) == str.charAt(1) && str.charAt(0) == str.charAt(2) && str.charAt(0) == str.charAt(3)) {
            return -1;
        }
        if (newArg == 6174) {
            return 0;
        }
        int i = 1;
        newArg = numberK(str);
        while (newArg != 6174) {
            newArg = numberK(Integer.toString(newArg));
            i++;
        }
        return i;
    }

    public static int numberK(@NotNull String arg) {
        char[] newArg = arg.toCharArray();
        Arrays.sort(newArg);
        int result = 0;
        result += ((newArg[3] - '0') - (newArg[0] - '0')) * Math.pow(10, 3);
        result += ((newArg[2] - '0') - (newArg[1] - '0')) * Math.pow(10, 2);
        result += ((newArg[1] - '0') - (newArg[2] - '0')) * Math.pow(10, 1);
        result += ((newArg[0] - '0') - (newArg[3] - '0')) * Math.pow(10, 0);
        return result;
    }
}
