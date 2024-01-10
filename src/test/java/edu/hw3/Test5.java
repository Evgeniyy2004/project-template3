package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static edu.hw3.Task5.parseContacts;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test5 {

    @Test
    @DisplayName("Если фамилия отсутствует, используется имя ")
    void maybeNoSecondNameSort() {
        var first = parseContacts(new String [] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, DescendingAscending.ASCENDING);
        var firstResult = Arrays.stream(first).map(x -> x.name).toArray();
        assertThat(firstResult).containsExactly("Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke");

        var second = parseContacts(new String [] {"Paul", "Leonhard Euler", "Carl Gauss"}, DescendingAscending.DESCENDING);
        var secondResult = Arrays.stream(second).map(x -> x.name).toArray();
        assertThat(secondResult).containsExactly("Paul","Carl Gauss", "Leonhard Euler");

    }
}
