package edu.hw8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Selector;
import java.util.Map;
import java.util.Scanner;
import static java.util.Map.entry;

public class Client{


    public static void main() throws IOException {
        try (var socket = new Socket("localhost",49001)) {
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
                System.out.println("Server replied "
                    + in.readLine());
            }
            sc.close();
        }
    }

    /*public void answer(String who, Scanner in) {
        System.out.print(who+":");
        var c = in.nextLine().replace("\n","");
        var res = (PHRASES.get(c) == null) ? "Лучше проигнорировать" : PHRASES.get(c);
        System.out.println("Сервер:"+res);
    }*/
}
