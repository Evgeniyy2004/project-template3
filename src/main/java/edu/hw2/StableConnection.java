package edu.hw2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        LOGGER.info("Успешно!");
    }

    @Override
    public void close() {
        LOGGER.info("Соединение закрыто");
    }
}
