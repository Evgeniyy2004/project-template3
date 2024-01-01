package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test7 {

    @Test
    @DisplayName("Благодаря NullComparer в TreeMap можно добавить null вместо ключа-строки")
    void onlyOneTest() {
        TreeMap<String, String> tree = new TreeMap<>(new NullComparer());
        tree.put(null, "test");
        tree.put(null, "another");
        assertThat(tree.containsKey(null)).isTrue();
    }
}
