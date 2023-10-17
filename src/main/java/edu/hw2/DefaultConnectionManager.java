package edu.hw2;

public class DefaultConnectionManager implements ConnectionManager {

    boolean fault = true;

    @Override
    public Connection getConnection() {
        fault = !fault;
        if (!fault) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }
    }
}
