package edu.hw6;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import static org.apache.commons.io.FileUtils.directoryContains;

public class DiskMap implements  Map<String, String>{


    private HashMap<String, String> associativeArray = new HashMap<>();
    Path path;
    public DiskMap() {
        path = Paths.get("DiskMap.txt").toAbsolutePath();
        int i = 0;
        try {
            while (directoryContains(path.getParent().toFile(), path.toFile())) {
                path = Paths.get("DiskMap" + (i + 1) + ".txt").toAbsolutePath();
                i += 1;
            }
            Files.write(path,"".getBytes());
        } catch (IOException e) {
        }
    }

    public DiskMap(Path pathToFile) {
        path = pathToFile;
        try {
        List<String> all = Files.readAllLines(pathToFile);
        for (int j = 0; j < all.size(); j++) {
            var currPair = all.get(j).split(":");
            associativeArray.put(currPair[0], currPair[1]);
        }
        } catch (IOException e) {
        }
    }

    @Override
    public int size() {
        return associativeArray.size();
    }

    @Override
    public boolean isEmpty() {
        return associativeArray.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return associativeArray.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return associativeArray.containsValue(value);
    }

    @Override
    public String get(Object key) {
        var a = associativeArray.get(key);
        return a;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        var a = associativeArray.put(key, value);
        WriteAgain();
        return a;
    }

    @Override
    public String remove(Object key) {
        if (associativeArray.containsKey(key)) {
            var  c = associativeArray.remove(key);
            WriteAgain();
            return c;
        }
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        associativeArray.putAll(m);
        WriteAgain();
    }

    @Override
    public void clear() {
        associativeArray.clear();
        WriteAgain();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return associativeArray.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return associativeArray.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return associativeArray.entrySet();
    }

    public void WriteAgain() {
        try {
            path.toFile().delete();
            path.toFile().createNewFile();
            var streamOfWrite = new FileOutputStream(path.toFile());
            for (String s : associativeArray.keySet()) {
                var curr = s + ":" + associativeArray.get(s)+"\n";
                streamOfWrite.write(curr.getBytes());
            }
        } catch (IOException e) {
        }
    }
}
