package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.jetbrains.annotations.NotNull;


public class Task2 {

    private Task2() {

    }

    public static String[] clusterize(@NotNull String str) {
        Stack<Character> brackets = new Stack<Character>();
        List<String> result = new ArrayList<String>();
        StringBuilder curr = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                brackets.push(str.charAt(i));
            } else {
                brackets.pop();
            }
            curr.append(str.charAt(i));
            if (brackets.isEmpty()) {
                result.add(new String(curr));
                curr = new StringBuilder();
            }
        }
  /*      if(!curr.isEmpty()) {
            result.add(new String(curr));
        }*/
        return result.toArray(new String[result.size()]);
    }
}
