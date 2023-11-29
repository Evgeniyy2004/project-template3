package edu.hw8;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class Server {

    public static ServerSocket SERVER = null;
    public void run() {
        try {
            var server = new ServerSocket(49001);
            SERVER = server;
            server.setReuseAddress(true);
            //var pool = Executors.newFixedThreadPool(5);
            // running infinite loop for getting
            // client request
            //Selector sel = Selector.open();
            var services = Executors.newFixedThreadPool(2);
            while (true) {
                services.execute(() -> {
                        try {
                            Socket client = server.accept();
                            ClientHandler clientSock
                                = new ClientHandler(client);
                            new Thread(clientSock).start();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                );

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
