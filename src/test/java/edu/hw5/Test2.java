package edu.hw5;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task2.allFridays13th;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class Test2 {
    @Test
    @DisplayName("Результатом являются все пятницы, выпадающие на 13 число только в заданном году")
    void onlyOneYear(){
        var one = allFridays13th(1925).toArray();
        for (int i = 0; i < one.length; i++) {
            var calendar = Calendar.getInstance();
            var curr = ((Date) one[i]).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            calendar.set(curr.getYear(), curr.getMonthValue()-1, curr.getDayOfMonth());
            assertThat(calendar.get(Calendar.YEAR)).isEqualTo(1925);
            assertThat(calendar.get(Calendar.DAY_OF_MONTH)).isEqualTo(13);
            var dayOf = calendar.get(Calendar.DAY_OF_WEEK);
            assertThat(dayOf).isEqualTo(Calendar.FRIDAY);
        }

        var two = allFridays13th(2024).toArray();
        for (int i = 0; i < two.length; i++) {
            var calendar = Calendar.getInstance();
            var curr = ((Date) two[i]).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            calendar.set(curr.getYear(), curr.getMonthValue()-1, curr.getDayOfMonth());
            assertThat(calendar.get(Calendar.YEAR)).isEqualTo(2024);
            assertThat(calendar.get(Calendar.DAY_OF_MONTH)).isEqualTo(13);
            var dayOf = calendar.get(Calendar.DAY_OF_WEEK);
            assertThat(dayOf).isEqualTo(Calendar.FRIDAY);
        }
    }

    @Test
    @DisplayName("Метод работает только с годами нашей эры")
    void onlyPositive(){
        try {
            allFridays13th(-1);
            fail("Год должен быть положительным");
        } catch (IllegalArgumentException expected) {
        }

        try {
            Task2.next(-1,1 , 1);
            fail("Год должен быть положительным");
        } catch (IllegalArgumentException expected) {
        }
    }

    @Test
    @DisplayName("Метод next возвращает следующую пятницу 13-е, независимо от того, будет она в том же году или нет")
    void next(){
        var c = Task2.next(2004, 12, 2);
        assertThat(c.getYear()).isEqualTo(2005);
        assertThat(c.getMonthValue()).isEqualTo(5);
        assertThat(c.getDayOfMonth()).isEqualTo(13);
    }
}
