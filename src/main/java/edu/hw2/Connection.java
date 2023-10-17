package edu.hw2;

public interface Connection extends AutoCloseable {
    void execute(String command);
}


