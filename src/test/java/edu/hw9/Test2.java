package edu.hw9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Test2 {

    @Test
    @DisplayName("При многопоточном обходе файловой системы выводятся имена папок, в которых содержится количество файлов, большее 10,  а также количество файлов")
    void checkIfOnlyTenAndMore() throws InterruptedException {
        var t = new Thread(()->Task2.go());
        t.start();
        t.join();
    }
}
