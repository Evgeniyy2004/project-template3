package edu.project3;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.stream.Stream;

public class LogAnalyst {

    HashMap<String, Long> sources = new HashMap<>();
    HashMap<String, Integer> codeOfRequestAnswer = new HashMap<>();
    List<LogRecord> result = new Vector<>();

    public Stream<LogRecord> ngixStats() throws IOException {
        Scanner terminalInput = new Scanner(System.in);
        List<LogRecord> result = new Vector<>();
        HashMap<LogRecord, String> howToPrint = new HashMap<>();
        HashMap<String, Long> resources = new HashMap<>();
        HashMap<Long, Long> codeOfRequestAnswer = new HashMap<>();
        for (int i = 0; i < 1; i++) {
            var curr = terminalInput.nextLine();
            var all = curr.split("--");
            var stream = Arrays.stream(all);
            var need = stream.filter(r -> !r.isEmpty()).skip(1);
            String way = String.valueOf(need.findFirst());
            var to = Arrays.stream(all).filter(r -> r.startsWith("to")).findFirst();
            var from = Arrays.stream(all).filter(r -> r.startsWith("from")).findFirst();
            var format = Arrays.stream(all).filter(r -> r.startsWith("format")).findFirst();
            way = way.replace("path", "");
            String [] currInfo = new String[0];
            try {
                var currStream = new URL(way).openStream();
                currInfo = new String(currStream.readAllBytes(), StandardCharsets.UTF_8).split("\n");

            } catch (IOException e) {

                var currStream = new File(way);
                currInfo = new String(Files.readAllBytes(currStream.toPath()), StandardCharsets.UTF_8).split("\n");
            } finally {
                for (int k = 0; k < currInfo.length; k++) {
                    var now = new LogRecord(Level.ALL, currInfo[i]);
                    if (format.isEmpty()) {
                        howToPrint.put(now, "adoc");
                    } else {
                        if (String.valueOf(format).contains("adoc")) {
                            howToPrint.put(now, "adoc");
                        } else {
                            howToPrint.put(now, "markdown");
                        }
                    }
                    if (codeOfRequestAnswer.containsKey(now.getLongThreadID())) {
                        codeOfRequestAnswer.put(now.getLongThreadID(), 1L);
                    } else {
                        codeOfRequestAnswer.put(
                            now.getLongThreadID(),
                            codeOfRequestAnswer.get(now.getLongThreadID()) + 1
                        );
                    }
                    if (resources.containsKey(now.getResourceBundleName())) {
                        resources.put(now.getResourceBundleName(), 1L);
                    } else {
                        resources.put(now.getResourceBundleName(), resources.get(now.getResourceBundleName()) + 1);
                    }
                    result.add(now);
                }
            }
        }
    }

    private String getStartDir(String path) throws IOException {
        int firstAsteriskIndex = path.indexOf("*");
        int lastSlashIndex = path.lastIndexOf("/", firstAsteriskIndex);
        //return path.substring(0, lastSlashIndex);
        Path dir = Path.of(path.substring(0, lastSlashIndex)).toAbsolutePath();
        Files.newDirectoryStream(dir, path).forEach(System.out::println);

        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.txt");
        Files.walkFileTree(dir, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (pathMatcher.matches(file)) {
                    System.out.println(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
