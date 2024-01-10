package edu.hw3;

import edu.hw1.EvenArrayUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task1.atbash;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test1 {
    @Test
    @DisplayName("Пустая строка и строка без символов латинского алфавита не меняются")
    void noChanges() {
        assertThat(atbash("")).isEqualTo("");
        assertThat(atbash("Игорь Гофман 7-8 !")).isEqualTo("Игорь Гофман 7-8 !");
    }

    @Test
    @DisplayName("Регистр букв сохраняется ")
    void upperCaseIsUpperCase() {
        assertThat(atbash("Hello world!")).isEqualTo("Svool dliow!");
        assertThat(atbash("Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler")).isEqualTo("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
    }

}
