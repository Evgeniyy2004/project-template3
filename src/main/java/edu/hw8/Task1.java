package edu.hw8;

import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class Task1{

    ServerSocket server = new ServerSocket();
    public Task1() throws IOException {
        for (int y = 49001; y <= 49150; y++) {
            try (Socket ignored = new Socket("localhost", y)) {
            } catch (ConnectException e) {
                server = new ServerSocket(y);
                break;
            } catch (IOException e) {
                throw new IllegalStateException("Error while trying to check open port", e);
            }
        }
    }

    public void run(String who) throws IOException {
       /* var socket = new Socket("localhost",server.getLocalPort());*/
        /*PrintWriter out = new PrintWriter(
            socket.getOutputStream(), true);

        // reading from server
        BufferedReader in
            = new BufferedReader(new InputStreamReader( System.in));*/

        // object of scanner class
        Scanner sc = new Scanner(System.in);
        var curr = sc.nextLine();
        /*String line = null;
        System.out.println("ff");
        while (!"exit".equalsIgnoreCase(line)) {

            // reading from user
            //line = in.readLine();
            sc.next();*/
            // sending the user input to server
            /*out.println(line);
            out.flush();

            // displaying server reply
            System.out.println("Server replied "
                + in.readLine());
        }
        server.close();*/
    }
}
