package edu.hw1;

import org.jetbrains.annotations.NotNull;
import java.util.Arrays;

public final class Task3 {
   private Task3() {
       //not used
   }

    public static boolean isNestable(int @NotNull  [] one, int @NotNull[] two) {
        Arrays.sort(one);
        Arrays.sort(two);
        if (one.length > 0 && two.length > 0 && one[0] > two[0] && one[one.length - 1] <  two[two.length - 1]) {
            return true;
        }
        return false;
    }
}
