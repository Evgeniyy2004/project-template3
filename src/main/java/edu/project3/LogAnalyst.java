package edu.project3;

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
import java.util.Scanner;
import java.util.logging.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LogAnalyst {

    private static final String LOG_ENTRY_PATTERN =
        "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\S+) (\\d+)\"(.*?)\" \"(.*?)\"$";
    private static final String FOR_REQUEST = "^([A-Z])+(\\S)+(.*)(\\S)+HTTP\\/([0-9])+.[0-9]$";
    private static final Pattern pattern = Pattern.compile(LOG_ENTRY_PATTERN);
    private static final Pattern newPattern = Pattern.compile(FOR_REQUEST);

    public Stream<LogRecord> ngixStats() throws IOException {
        Scanner terminalInput = new Scanner(System.in);
        HashMap<String, Long> resources = new HashMap<>();
        HashMap<Integer, Long> codeOfRequestAnswer = new HashMap<>();
        for (int i = 0; i < 1; i++) {
            var curr = terminalInput.nextLine();
            var all = curr.split("--");
            var stream = Arrays.stream(all);
            var need = stream.filter(r -> !r.isEmpty()).skip(1);
            String way = String.valueOf(need.findFirst());
            var to = Arrays.stream(all).filter(r -> r.startsWith("to")).findFirst();
            var from = Arrays.stream(all).filter(r -> r.startsWith("from")).findFirst();
            var format = Arrays.stream(all).filter(r -> r.startsWith("format")).findFirst();
            long sumOfAnswersSizes = 0;
            way = way.replace("path", "");

            try {
                String[] currInfo;
                var currStream = new URL(way).openStream();
                currInfo = new String(currStream.readAllBytes(), StandardCharsets.UTF_8).split("\n");
                for (int k = 0; k < currInfo.length; k++) {
                    Matcher matcher = pattern.matcher(currInfo[k]);
                    String requestLine = matcher.group(5);
                    String statusCode = matcher.group(6);
                    String bytes = matcher.group(7);
                    Matcher newMatcher = newPattern.matcher(requestLine);
                    String resource = newMatcher.group(3).replace(" ", "");
                    if (resources.containsKey(resource)) {
                        resources.put(resource, resources.get(resource) + 1);
                    } else {
                        resources.put(resource, 1L);
                    }
                    sumOfAnswersSizes += Integer.parseInt(bytes);
                    var code = Integer.parseInt(statusCode);
                    if (codeOfRequestAnswer.containsKey(code)) {
                        codeOfRequestAnswer.put(code, codeOfRequestAnswer.get(code) + 1);
                    } else {
                        codeOfRequestAnswer.put(code, 1L);
                    }
                }
                sumOfAnswersSizes /= currInfo.length;
            } catch (IOException e) {

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
