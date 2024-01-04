package edu.hw8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import static java.util.Map.entry;

@Slf4j
public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private static final String EXIT = "exit";
    private static final Map<String, String> PHRASES = Map.ofEntries(
        entry("личности", "Не переходи на личности там, где их нет"),
        entry(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма"
        ),
        entry("интеллект", "Чем ниже интеллект, тем громче оскорбления"),
        entry(
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
        )
    );

    // Constructor
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            // get the outputstream of client
            out = new PrintWriter(
                System.out, true);

            // get the inputstream of client
            in = new BufferedReader(
                new InputStreamReader(
                    System.in));

            String line = "";
            while (!Objects.equals(line, EXIT)) {
                // writing the received message from
                // client
                log.info(clientSocket.getInetAddress().getHostName() + ":" + line);
                //out.println(line);
                var answer =
                    (PHRASES.get(line) == null) ? "Переубедить вас мне удастся, поэтому сразу перейду к оскорблениям."
                        : PHRASES.get(line);
                if (Objects.equals(line, EXIT)) {
                    break;
                }
                out.println(answer);
                line = in.readLine();
            }
        } catch (IOException e) {
            log.info(e.getMessage(), e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                log.info(e.getMessage(), e);
            }
        }
    }
}