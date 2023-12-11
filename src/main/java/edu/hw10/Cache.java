package edu.hw10;

public @interface Cache {
    boolean persist()  default false;
}
