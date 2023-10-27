package edu.hw3;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Task6  implements StockMarket {

    private PriorityQueue<Stock> allStocks = new PriorityQueue<>(new StockComparer());

    HashMap<String, Stock> allCompanies = new HashMap<String, Stock>();

    @Override
    public void add(Stock stock) {
        if (allCompanies.containsKey(stock.company)) {
            var toDelete = allCompanies.get(stock.company);
            allStocks.remove(toDelete);
            allStocks.add(stock);
            allCompanies.put(stock.company, stock);
        }  else {
            allStocks.add(stock);
            allCompanies.put(stock.company, stock);
        }
    }

    @Override
    public void remove(Stock stock) {
        if (allCompanies.containsKey(stock.company) && allCompanies.get(stock.company) == stock) {
            var toDelete = allCompanies.get(stock.company);
            allStocks.remove(toDelete);
            allCompanies.remove(stock.company);
        }
    }

    @Override
    public Stock mostValuableStock() {
        if (allStocks.isEmpty()) {
            throw new RuntimeException("Акций здесь нет");
        } else {
            return allStocks.peek();
        }
    }
}
