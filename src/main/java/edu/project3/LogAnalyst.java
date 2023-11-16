package edu.project3;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LogAnalyst {

    HashMap<String, Long> sources = new HashMap<>();
    HashMap<String,Integer> codeOfRequestAnswer = new HashMap<>();
    List<LogRecord> result = new Vector<>();
    public  void ngixStats() {
        Scanner terminalInput = new Scanner(System.in);
        int s = Integer.parseInt(terminalInput.nextLine());
        List<LogRecord> result = new Vector<>();
        HashMap<LogRecord, String> howToPrint = new HashMap<>();
        HashMap<String,Long> sources = new HashMap<>();
        HashMap<String,Integer> codeOfRequestAnswer = new HashMap<>();
        for (int i = 0; i < s; i++) {
            var curr = terminalInput.nextLine();
            var all = curr.split("--");
            var stream = Arrays.stream(all);
            var need = stream.skip(1).filter(r -> !r.isEmpty());
            String way = String.valueOf(need.findFirst());
            var to = Arrays.stream(all).filter(r->r.startsWith("to")).findFirst();
            var from = Arrays.stream(all).filter(r->r.startsWith("from")).findFirst();
            var format = Arrays.stream(all).filter(r->r.startsWith("format")).findFirst();
            String [] allRequests;
            try {
                way = way.replace("path","");
                var currStream = new URL(way).openStream();
                String[] currInfo = new String(currStream.readAllBytes(), StandardCharsets.UTF_8).split("\n");
                for (int k = 0; k < currInfo.length; k++) {
                    var now = new LogRecord(Level.ALL,currInfo[i]);
                }
            } catch (IOException e){

            }
        }
    }
}
