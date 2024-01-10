package edu.hw3;

import org.jetbrains.annotations.NotNull;

public class Stock implements  Comparable<Stock> {
    public final String company;
    double price;

    public Stock(String name, double costs) {
        price = costs;
        company = name;
    }

    @Override
    public int compareTo(@NotNull Stock another) {
        if (another.price > this.price) {
            return 1;
        } else {
            return -1;
        }
    }

}
