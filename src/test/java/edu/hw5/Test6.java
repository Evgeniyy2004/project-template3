package edu.hw5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task6.partOf;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test6 {
    @Test
    @DisplayName("Подпоследовательность строки - последовательность символов строки, идущих друг за другом, но необязательно подряд")
    void checkNumbers() {
        assertTrue(partOf("abc","a12322323b78657868686dgdfhdfc"));
        assertFalse(partOf("7856","a12322323b7865dgdfhdfc"));
    }
}
