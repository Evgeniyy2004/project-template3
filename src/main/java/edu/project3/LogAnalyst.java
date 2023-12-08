package edu.project3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import net.steppschuh.markdowngenerator.table.Table;

@SuppressWarnings({"MultipleStringLiterals"})
@Slf4j
public class LogAnalyst {

    private LogAnalyst() {
    }

    private static final String LOG_ENTRY_PATTERN =
        "^(.*) - - \\[([\\w:/]+\\s[+\\-]\\d{4})\\] (\\S+)\"(.+?)\" "
            + "(\\d{3}) (\\S+) (\\d+)(\\S+)\"(.*?)\"(\\S+)\"(.*?)\"$";
    private static final String FOR_REQUEST = "^[A-Z]+( +)(\\w)( +)(.*)$";

    private static final String FROM = "Начальная дата";
    private static final String TO = "Конечная дата";
    private static final String FILES = "Файл(-ы)";
    private static final String RESULT = "result.adoc";

    public static void ngixStats(String curr) throws IOException, ParseException {
        HashMap<String, Long> resources = new HashMap<>();
        HashMap<Integer, Long> codeOfRequestAnswer = new HashMap<>();
        for (int i = 0; i < 1; i++) {
            var all = curr.split("--");
            var stream = Arrays.stream(all);
            var need = stream.filter(r -> !r.isEmpty()).skip(1);
            String way = need.findFirst().get();
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
                    var no = Arrays.stream(currInfo[k].split("\"")).filter(r -> !Objects.equals(r, "")).toArray();
                    String requestLine = (String) no[1];
                    var cool = Arrays.stream(((String) no[2]).split(" ")).filter(r -> !Objects.equals(r, "")).toArray();
                    String statusCode = (String) cool[0];
                    String bytes = (String) cool[1];
                    String resource = (String) Arrays.stream(requestLine.split(" "))
                        .filter(r -> !Objects.equals(r, "")).toArray()[1];
                    if (resources.containsKey(resource)) {
                        resources.put(resource, resources.get(resource) + 1);
                    } else {
                        resources.put(resource, 1L);
                    }
                    sumOfAnswersSizes += Long.parseLong(bytes);
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
                var startDir = getStartDir(way);
                List<String> matchesList = new ArrayList<String>();
                String finalWay = way.replace(" ", "");
                workWithTree(startDir, matchesList, finalWay);
                for (String p : matchesList) {
                    var current = new File(p).toPath().toAbsolutePath();
                    var currInfo = new String(Files.readAllBytes(current), StandardCharsets.UTF_8).split("\n");
                    for (int k = 0; k < currInfo.length; k++) {
                        var no = Arrays.stream(currInfo[k].split("\"")).filter(r -> !Objects.equals(r, "")).toArray();
                        String requestLine = (String) no[1];
                        var cool =
                            Arrays.stream(((String) no[2]).split(" ")).filter(r -> !Objects.equals(r, "")).toArray();
                        String statusCode = (String) cool[0];
                        String bytes = (String) cool[1];
                        String resource =
                            (String) Arrays.stream(requestLine.split(" ")).filter(r -> !Objects.equals(r, ""))
                                .toArray()[1];
                        if (resources.containsKey(resource)) {
                            resources.put(resource, resources.get(resource) + 1);
                        } else {
                            resources.put(resource, 1L);
                        }
                        sumOfAnswersSizes += Long.parseLong(bytes);
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
            }
            var sortedResources = resources.entrySet().stream().sorted((f1, f2) -> Math.toIntExact(
                f2.getValue() - f1.getValue())).toList();
            var sorted1 = codeOfRequestAnswer.entrySet().stream().sorted((f1, f2) -> Math.toIntExact(
                f2.getValue() - f1.getValue())).toList();
            var start = (from.isEmpty()) ? "-"
                : LocalDate.parse(from.get().replace("from", "").replace(" ", "")).toString();
            var end =
                (to.isEmpty()) ? "-" : LocalDate.parse(to.get().replace("to", "").replace(" ", "")).toString();
            if (format.isEmpty() || format.get().contains("markdown")) {
                makeMarkDown(way, start, end, count, sumOfAnswersSizes, sorted1, sortedResources);
            } else {
                makeAdoc(way, start, end, count, sumOfAnswersSizes, sortedResources, sorted1);
            }
        }
    }

    private static void workWithTree(String startDir, List<String> matchesList, String finalWay) throws IOException {
        FileVisitor<Path> matcherVisitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                var fs = Paths.get(startDir).getFileSystem();
                PathMatcher matcher = fs.getPathMatcher("glob:" + finalWay.replace("\\", "/"));
                Path name = file.toAbsolutePath();
                if (matcher.matches(name)) {
                    matchesList.add(name.toString());
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exp) {
                return FileVisitResult.CONTINUE;
            }
        };
        Files.walkFileTree(Paths.get(startDir), matcherVisitor);
    }

    private static String getStartDir(String path) throws IOException {
        int firstAsteriskIndex = path.indexOf("*");
        int lastSlashIndex =
            (firstAsteriskIndex != -1) ? path.lastIndexOf("\\", firstAsteriskIndex) : path.lastIndexOf("\\");
        return path.substring(0, lastSlashIndex).replace(" ", "");

    }

    private static void makeAdoc(
        String way, String start, String end,
        long count, long sumOfAnswersSizes, List<Map.Entry<String, Long>> sortedResources,
        List<Map.Entry<Integer, Long>> sorted1
    ) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("[cols = 2]\n");
        result.append("|===\n");
        result.append("|" + FILES + "\n");
        result.append("|" + way + "\n");
        result.append("\n");
        result.append("|" + FROM + "\n");
        result.append("|" + start + "\n");
        result.append("\n");
        result.append("|" + TO + "\n");
        result.append("|" + end + "\n");
        result.append("\n");
        result.append("|" + "Количество запросов\n");
        result.append("|" + count + "\n");
        result.append("\n");
        result.append("|" + "Средний размер ответа\n");
        result.append("|" + sumOfAnswersSizes + "\n");
        result.append("|===\n");

        StringBuilder result1 = new StringBuilder();
        result1.append("[cols = 2]\n");
        result1.append("|===\n");
        result1.append("|" + "Ресурс\n");
        result1.append("|" + "Количество" + "\n");
        result1.append("\n");
        for (int j = 0; j < (2 + 1) && j < sortedResources.size(); j++) {
            if (j != 2 && j != sortedResources.size() - 1) {
                result1.append("|" + sortedResources.get(j).getKey() + "\n");
                result1.append("|" + sortedResources.get(j).getValue() + "\n");
                result1.append("\n");
            } else {
                result1.append("|" + sortedResources.get(j).getKey() + "\n");
                result1.append("|" + sortedResources.get(j).getValue() + "\n");
            }
        }
        result1.append("|===\n");

        StringBuilder result2 = new StringBuilder();
        result2.append("[cols = 2]\n");
        result2.append("|===\n");
        result2.append("|" + "Код\n");
        result2.append("|" + "Количество" + "\n");
        result2.append("\n");
        for (int j = 0; j < (2 + 1) && j < sorted1.size(); j++) {
            if (j != 2 && j != sorted1.size() - 1) {
                result2.append("|" + sorted1.get(j).getKey() + "\n");
                result2.append("|" + sorted1.get(j).getValue() + "\n");
                result2.append("\n");
            } else {
                result2.append("|" + sorted1.get(j).getKey() + "\n");
                result2.append("|" + sorted1.get(j).getValue() + "\n");

            }
        }
        result2.append("|===\n");
        var file = new File(RESULT);
        if (file.createNewFile()) {
            var writer = new FileOutputStream(RESULT);
            writer.write((result + "\n" + result1 + "\n" + result2).getBytes(StandardCharsets.UTF_8));
            writer.close();
        } else {
            file.delete();
            file.createNewFile();
            var writer = new FileOutputStream(RESULT);
            writer.write((result + "\n" + result1 + "\n" + result2).getBytes(StandardCharsets.UTF_8));
            writer.close();
        }
    }

    private static void makeMarkDown(
        String way, String start, String end, long count,
        long sumOfAnswersSizes, List<Map.Entry<Integer, Long>> sorted1,
        List<Map.Entry<String, Long>> sortedResources
    ) {
        Table.Builder builder = new Table.Builder()
            .withAlignments(Table.ALIGN_CENTER, Table.ALIGN_RIGHT)
            .addRow("Метрика", "Значение")
            .addRow(FILES, way)
            .addRow(FROM, start)
            .addRow(TO, end)
            .addRow("Количество запросов", count)
            .addRow("Средний размер ответа", sumOfAnswersSizes + "b");

        Table.Builder builder1 = new Table.Builder()
            .withAlignments(Table.ALIGN_CENTER, Table.ALIGN_CENTER)
            .addRow("Ресурс", "Количество");
        for (int j = 0; j < (2 + 1) && j < sortedResources.size(); j++) {
            builder1.addRow(sortedResources.get(j).getKey(), sortedResources.get(j).getValue());
        }

        Table.Builder lastBuilder = new Table.Builder()
            .withAlignments(Table.ALIGN_CENTER, Table.ALIGN_CENTER)
            .addRow("Код", "Количество");
        for (int j = 0; j < (2 + 1) && j < sorted1.size(); j++) {
            lastBuilder.addRow(sorted1.get(j).getKey(), sorted1.get(j).getValue());
        }
        log.info(String.valueOf(builder.build()));
        log.info("\n");
        log.info(String.valueOf(builder1.build()));
        log.info("\n");
        log.info(String.valueOf(lastBuilder.build()));
    }
}
