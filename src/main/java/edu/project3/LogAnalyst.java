package edu.project3;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.steppschuh.markdowngenerator.table.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.asciidoctor.Asciidoctor;

public class LogAnalyst {

    private static final String LOG_ENTRY_PATTERN =
        "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\S+) (\\d+)\"(.*?)\" \"(.*?)\"$";
    private static final String FOR_REQUEST = "^([A-Z])+(\\S)+(.*)(\\S)+HTTP\\/([0-9])+.[0-9]$";
    private final static Logger LOGGER = LogManager.getLogger();
    private static final Pattern pattern = Pattern.compile(LOG_ENTRY_PATTERN);
    private static final Pattern newPattern = Pattern.compile(FOR_REQUEST);

    public void ngixStats() throws IOException, ParseException {
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
            long count = 0;
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
                count = currInfo.length;
            } catch (IOException e) {

                var startdir = getStartDir(way);
                List<String> matchesList = new ArrayList<String>();
                String finalWay = way;
                FileVisitor<Path> matcherVisitor = new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                        FileSystem fs = FileSystems.getDefault();
                        PathMatcher matcher = fs.getPathMatcher(finalWay);
                        Path name = file.getFileName();
                        if (matcher.matches(name)) {
                            matchesList.add(name.toString());
                        }
                        return FileVisitResult.CONTINUE;
                    }
                };
                Files.walkFileTree(new File(startdir).toPath().toAbsolutePath(), matcherVisitor);
                for (String p : matchesList) {
                    var current = new File(p).toPath().toAbsolutePath();
                    var currInfo = new String(Files.readAllBytes(current), StandardCharsets.UTF_8).split("\n");
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
                        count++;
                    }
                    sumOfAnswersSizes /= count;
                }
                var sortedResources = resources.entrySet().stream().sorted((f1, f2) -> Math.toIntExact(
                    f2.getValue() - f1.getValue())).toList();
                var sorted1 =codeOfRequestAnswer.entrySet().stream().sorted((f1, f2) -> Math.toIntExact(
                    f2.getValue() - f1.getValue())).toList();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                var  start = (from.isEmpty()) ? "-" : dateFormat.parse(from.get()).toString();
                var end = (to.isEmpty()) ? "-" : dateFormat.parse(to.get()).toString();
                if (format.isEmpty() || format.get().contains("markdown"))  {
                    Table.Builder builder = new Table.Builder()
                        .withAlignments(Table.ALIGN_CENTER,  Table.ALIGN_RIGHT)
                        .addRow("Метрика", "Значение")
                        .addRow("Файл(-ы)", way)
                        .addRow("Начальная дата", start)
                        .addRow("Конечная дата", end)
                        .addRow("Количество запросов", count)
                        .addRow("Средний размер ответа", sumOfAnswersSizes+"b");

                    Table.Builder builder1 = new Table.Builder()
                        .withAlignments(Table.ALIGN_CENTER,  Table.ALIGN_CENTER)
                        .addRow("Ресурс", "Количество");
                    for (int j = 0; j < 3 && j < sortedResources.size(); j++) {
                        builder1.addRow(sortedResources.get(j).getKey(),sortedResources.get(j).getValue());
                    }

                    Table.Builder lastBuilder = new Table.Builder()
                        .withAlignments(Table.ALIGN_CENTER, Table.ALIGN_CENTER)
                        .addRow("Код", "Количество");
                    for (int j = 0; j < 3 && j < sortedResources.size(); j++) {
                        lastBuilder.addRow(sorted1.get(j).getKey(),sorted1.get(j).getValue());
                    }
                } else {
                    Asciidoctor doctor = Asciidoctor.Factory.create();
                    doctor.
                }
            }
        }
    }

    private String getStartDir(String path) throws IOException {
        int firstAsteriskIndex = path.indexOf("*");
        int lastSlashIndex = path.lastIndexOf("/", firstAsteriskIndex);
        return path.substring(0, lastSlashIndex);

    }
}
