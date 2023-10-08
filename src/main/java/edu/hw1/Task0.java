package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public  final class Task0 {
    @SuppressWarnings("uncommentedmain")
    public static void main(String[]args) {
         Logger LOGGER = LogManager.getLogger();
         LOGGER.info("Привет, мир!");
    }

    private Task0() {
        //not called
    }
}
