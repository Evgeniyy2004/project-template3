package edu.hw3;

import java.util.Comparator;

public class StockComparer implements Comparator<Stock> {
    @Override
    public int compare(Stock first, Stock second) {
        if (first.price > second.price) {
            return -1;
        }
        if (first.price < second.price) {
            return 1;
        } else {
            return first.company.compareTo(second.company);
        }
    }
}
