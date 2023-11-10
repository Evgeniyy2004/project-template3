package edu.hw6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import static org.apache.commons.io.FileUtils.directoryContains;

public class DiskMap implements  Map<String, String>{


    private HashMap<String, String> associativeArray = new HashMap<>();
    Path path;
    public DiskMap() {
        path = Paths.get("DiskMap.txt");
        int i = 0;
        try {
            while (directoryContains(path.getParent().toFile(), path.toFile())) {
                path = Paths.get("DiskMap" + i + ".txt");
                i += 1;
            }
            Files.write(path,"".getBytes());
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
        return associativeArray.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        return associativeArray.put(key, value);
    }

    @Override
    public String remove(Object key) {
        if (associativeArray.containsKey(key)) {
            return associativeArray.remove(key);
        }
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        associativeArray.putAll(m);
        try {
            WriteAgain();
        } catch (IOException exception) {
        }
    }

    @Override
    public void clear() {
        associativeArray.clear();
        try {
            WriteAgain();
        } catch (IOException exception) {
        }
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

    public void WriteAgain() throws IOException {
        path.toFile().delete();
        path.toFile().createNewFile();
        var streamOfWrite = new FileOutputStream(path.toFile());
        for (String s : associativeArray.keySet()) {
            var curr = s+":"+associativeArray.get(s);
            streamOfWrite.write(curr.getBytes());
        }
    }
}
