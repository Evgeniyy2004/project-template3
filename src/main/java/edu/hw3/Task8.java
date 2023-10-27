package edu.hw3;

import java.util.Collection;
import java.util.Iterator;

public class Task8<T> implements Iterator<T> {

    private int currIndex;
    private Collection<T> collectionForIterations;

    @Override
    public boolean hasNext()  {
        return currIndex != 0;
    }

    public Task8(Collection<T> collection) {
        currIndex = collection.size();
        collectionForIterations = collection;
    }

    @Override
    public T next() {
        if (currIndex - 1 >= 0) {
            currIndex--;
            return (T) collectionForIterations.toArray()[currIndex];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
}
