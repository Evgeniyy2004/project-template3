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

    private static final Map<String, String> PHRASES = Map.ofEntries(
        entry("глупый","А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."),
        entry("интелллект","Чем ниже интеллект, тем громче оскорбления"),
        entry("оскорбления", "Если твои противники перешли на личные оскорбления, будь уверен — твоя победа не за горами"),
        entry("личности", "Не переходи на личности там, где их нет")
    );


    public static void main(String who) throws IOException {
        try (var socket = new Socket("localhost",49001)) {
            PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);
            //reading from server
            BufferedReader in
                = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // object of scanner class*/
            Scanner sc = new Scanner(System.in);
            String line = null;
            System.out.println("ff");
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
