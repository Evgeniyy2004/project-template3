package edu.hw5;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task4.check;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test4 {
    @Test
    @NotNull("При наличии одного или нескольких указанных символов в строке возврщается true, иначе - false")
    void regular1() {
        assertFalse(check("38348348993939"));
        assertTrue(check("adbdb&abdad4747475"));
        assertTrue(check("абавбваавгавгавг%"));
    }
}
