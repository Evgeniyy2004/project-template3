package edu.hw6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("all")
public class Task6 {

    private final static Logger LOGGER = LogManager.getLogger();

    private Task6() {
    }

    @SuppressWarnings("all")
    public static boolean check(int port) {
        if (port > 65535 || port < 0) {
            throw new IllegalArgumentException();
        }

        HashMap<Integer, String> known = new HashMap<>();
        known.put(80, "HyperText Transfer Protocol");
        known.put(21, "File Transfer Protocol");
        known.put(25, "Simple Mail Transfer Protocol");
        known.put(22, "Secure Shell");
        known.put(443, "HyperText Transfer Protocol Secure");
        known.put(53, "Domain Name System");
        known.put(3306, "MySQL Database");
        known.put(5432, "PostgreSQL Database");
        known.put(3389, "Remote Desktop Protocol (RDP)");
        known.put(27017, "MongoDB Database");
        known.put(1521, "Oracle Database");
        known.put(49153, "Remote Procedure Call");
        known.put(23, "Telnet");
        known.put(110, "POP3");
        known.put(143, "IMAP");
        known.put(445, "SMB");
        HashMap<Integer, ServerSocket> unknown = new HashMap<>();
        for (int i = 0; i <= 49151; i++) {
            if (known.containsKey(i)) {
                continue;
            }
            try (Socket ignored = new Socket("localhost", i)) {
                known.put(i, "");
            } catch (IOException e) {
                try {
                    unknown.put(i, new ServerSocket(i));
                } catch (IOException exception) {

                }
            }
        }
        int curr = 1;
        LOGGER.info("Протокол   Порт   Сервис");
        for (int i = 0; i < 20; i++) {
            if (curr > 49151) {
                break;
            }
            String p = (curr % 2 == 0) ? "UDP" : "TCP";
            String s = known.getOrDefault(curr, "");
            String result = String.format("%8.8s  %5.5s  %1.50s", p, "" + curr, s);
            LOGGER.info(result);
            curr += Math.random() * 100;
        }

        try (Socket ignored = new Socket("localhost", port)) {
            for (int i : unknown.keySet()) {
                unknown.get(i).close();
            }
            return true;
        } catch (IOException e) {
            for (int i : unknown.keySet()) {
                try {
                    unknown.get(i).close();
                    unknown.get(i).close();
                } catch (IOException exception) {
                }
            }
            return false;
        }

    }
}
