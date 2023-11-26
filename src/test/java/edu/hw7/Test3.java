package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Test3 {

    @Test
    @DisplayName("Человек появляется в поиске тогда, когда его адрес, телефон и имя не равны null, т.е когда она содержится во всех словарях")
    void checkWhere() {
        //Arrange
        var base = new Task3();
        var one = new Person(123,null,"spb","9900");
        var two = new Person(3,"vasya","moskow","9900");

        //Act
        base.add(one);
        base.add(two);
        var res1 = base.findByAddress("spb");
        var res2 = base.findByName("vasya");

        //Assert
        assertThat(res1).isEmpty();
        assertThat(res2).contains(two);
    }
}
