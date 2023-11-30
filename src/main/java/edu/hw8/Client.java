package edu.hw8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {

    private Client() {

    }

    private static final int PORT = 49001;

    public static void main() throws IOException {
        try (var socket = new Socket("localhost", PORT)) {
            PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);
            //reading from server
            BufferedReader in
                = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // object of scanner class*/
            Scanner sc = new Scanner(System.in);
            String line = null;
            while (!"exit".equalsIgnoreCase(line)) {

                // reading from user
                line = sc.nextLine();
                //sending the user input to server
                out.println(line);
                out.flush();

                // displaying server reply
                log.info("Server replied "
                    + in.readLine());
            }
            sc.close();
        }
    }

}
