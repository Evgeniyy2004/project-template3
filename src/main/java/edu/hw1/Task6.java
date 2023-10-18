package edu.hw1;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public final class Task6 {

    private static final int KAP_CONSTANT = 6174;
    private static final int TYPICAL_LENGTH = 4;

    private static final int CURR_RADIX = 10;

    private Task6() {
        //not used
    }

    public static int countK(int arg) {
        int newArg = arg;
        if (newArg < 0) {
            newArg = Math.abs(newArg);
        }
        String str = Integer.toString(newArg);
        if (str.length() != TYPICAL_LENGTH || str.equals("1000")) {
            return -1;
        }
        if (str.charAt(0) == str.charAt(1)
            && str.charAt(0) == str.charAt(2) && str.charAt(0) == str.charAt(TYPICAL_LENGTH - 1)) {
            return -1;
        }
        if (newArg == KAP_CONSTANT) {
            return 0;
        }
        int i = 1;
        newArg = numberK(str);
        while (newArg != KAP_CONSTANT) {
            newArg = numberK(Integer.toString(newArg));
            i++;
        }
        return i;
    }

    public static int numberK(@NotNull String arg) {
        char[] newArg = arg.toCharArray();
        Arrays.sort(newArg);
        int result = 0;
        result += ((newArg[TYPICAL_LENGTH - 1] - '0') - (newArg[0] - '0')) * Math.pow(CURR_RADIX, TYPICAL_LENGTH - 1);
        result += ((newArg[2] - '0') - (newArg[1] - '0')) * Math.pow(CURR_RADIX, 2);
        result += ((newArg[1] - '0') - (newArg[2] - '0')) * Math.pow(CURR_RADIX, 1);
        result += ((newArg[0] - '0') - (newArg[TYPICAL_LENGTH - 1] - '0')) * Math.pow(CURR_RADIX, 0);
        return result;
    }
}
