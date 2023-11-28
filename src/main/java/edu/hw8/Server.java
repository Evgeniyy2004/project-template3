package edu.hw8;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void run() {
        ServerSocket server = null;

        try {
            server = new ServerSocket(49001);
            server.setReuseAddress(true);
            //var pool = Executors.newFixedThreadPool(5);
            // running infinite loop for getting
            // client request
            for(int y = 0; y <5 ; y++) {

                // socket object to receive incoming client
                // requests
                Socket client = server.accept();

                // Displaying that new client is connected
                // to server
                System.out.println("New client connected"
                    + client.getInetAddress()
                    .getHostName());

                // create a new thread object
                ClientHandler clientSock
                    = new ClientHandler(client);

                // This thread will handle the client
                // separately
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
