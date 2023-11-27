package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task2.clusterize;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test2 {
    @Test
    @DisplayName("Из пустой строки нельзя сделать кластеры")
    void noClusters() {
        assertThat(clusterize("")).containsExactly();
    }

    @Test
    @DisplayName("Новый кластер создаётся, когда в текущем кластере количество открывающих скобок равно количеству закрывающих")
    void workingWithNestedBrackets() {
        assertThat(clusterize("()()()")).containsExactly("()", "()", "()");
        assertThat(clusterize("((()))")).containsExactly("((()))");
        assertThat(clusterize("((()))(())()()(()())")).containsExactly("((()))", "(())", "()", "()", "(()())");
        assertThat(clusterize("((())())(()(()()))")).containsExactly("((())())", "(()(()()))");
    }
}
