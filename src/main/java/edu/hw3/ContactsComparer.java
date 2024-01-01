package edu.hw3;

import java.util.Comparator;
import static edu.hw3.Task5.toRightOrder;

public class ContactsComparer implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        var a = String.join(" ", toRightOrder(o1.toString().split(" ")));
        return a.compareTo(String.join(" ", toRightOrder(o2.toString().split(" "))));
    }
}
