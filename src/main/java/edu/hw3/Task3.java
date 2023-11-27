package edu.hw3;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import org.jetbrains.annotations.NotNull;


public class Task3<T> {
    public Dictionary<T, Integer> freqDict(@NotNull T[] all) {
       HashMap<T, Integer> result = new HashMap<T, Integer>();
        for (int i = 0; i < all.length; i++) {
            if (result.containsKey(all[i])) {
                result.put(all[i], result.get(all[i]) + 1);
            } else {
                result.put(all[i], 1);
            }
        }
        return new Hashtable<T, Integer>(result);
    }
}
