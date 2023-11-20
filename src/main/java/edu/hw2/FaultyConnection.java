package edu.hw2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements  Connection {

    private static final Logger LOGGER = LogManager.getLogger();
    boolean fault = true;

    @Override
    public void execute(String command) {
        fault = !fault;
        if (!fault) {
            throw new ConnectionException();
        } else {
            LOGGER.info("Успешно");
        }
    }

    @Override
    public void close() {
        LOGGER.info("Соединение закрыто");
    }
}
