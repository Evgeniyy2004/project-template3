package project2;

import java.awt.Point;

public class SinglyLinkedList {

    public Point curr;
    public SinglyLinkedList previous;
    public SinglyLinkedList(Point now, SinglyLinkedList prev) {
        curr = now;
        previous = prev;
    }
}
