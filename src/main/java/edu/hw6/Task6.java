package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.AbstractMap;
import java.util.HashMap;

public class Task6 {

    public static boolean check(int port) {
        if (port > 65535 || port < 0) {
            throw new IllegalArgumentException();
        }
        try {
            HashMap<Integer, AbstractMap.SimpleEntry<ServerSocket, DatagramSocket>> all =  new HashMap<>();
            all.put(80, new AbstractMap.SimpleEntry<>(new ServerSocket(80), new DatagramSocket(80)));
            //если порт занят при создании socket будет ioexception
            all.put(21, new AbstractMap.SimpleEntry<>(new ServerSocket(80), new DatagramSocket(21)));
            all.put(25, new AbstractMap.SimpleEntry<>(new ServerSocket(80), new DatagramSocket(25)));
            all.put(22, new AbstractMap.SimpleEntry<>(new ServerSocket(80), new DatagramSocket(22)));
            all.put(443, new AbstractMap.SimpleEntry<>(new ServerSocket(80), new DatagramSocket(443)));
            all.put(53, new AbstractMap.SimpleEntry<>(new ServerSocket(80), new DatagramSocket(53)));
            all.put(3306, new AbstractMap.SimpleEntry<>(new ServerSocket(80), new DatagramSocket(3306)));
            all.put(5432, new AbstractMap.SimpleEntry<>(new ServerSocket(80), new DatagramSocket(5432)));
            all.put(3389, new AbstractMap.SimpleEntry<>(new ServerSocket(80), new DatagramSocket(3389)));
            all.put(27017, new AbstractMap.SimpleEntry<>(new ServerSocket(80), new DatagramSocket(27017)));
            all.put(1521, new AbstractMap.SimpleEntry<>(new ServerSocket(80), new DatagramSocket(1521)));
        } catch (IOException e) {
        }
    }
}
