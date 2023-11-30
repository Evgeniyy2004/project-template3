package edu.hw8;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {

    public static ServerSocket server = null;
    private static final int PORT = 49001;

    public void run() {
        try {
            server = new ServerSocket(PORT);
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
            log.info(e.getMessage(), e);
        }
    }

}
