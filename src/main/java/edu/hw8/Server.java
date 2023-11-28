package edu.hw8;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class Server {

    public static void run() {
        try {
            var server = new ServerSocket(49001);
            server.setReuseAddress(true);
            //var pool = Executors.newFixedThreadPool(5);
            // running infinite loop for getting
            // client request
            var services = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            services.execute(() -> {
                    Socket client = null;
                    try {
                        client = server.accept();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ClientHandler clientSock
                        = new ClientHandler(client);

                    new Thread(clientSock).start();
                }
            );
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
