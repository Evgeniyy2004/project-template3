package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Test3 {

    @Test
    @DisplayName("Человек появляется в поиске тогда, когда его адрес, телефон и имя не равны null, т.е когда он содержится во всех словарях")
    void checkWhere() {
        //Arrange
        var base = new Task3();
        var one = new Person(123,null,"spb","9900");
        var two = new Person(3,"vasya","moskow","9900");
        var three = new Person(9,"leha",null,"9900");
        var four = new Person(32,"sasha","ekb",null);

        //Act
        base.add(one);
        base.add(two);
        base.add(three);
        base.add(four);
        var res1 = base.findByAddress("spb");
        var res2 = base.findByName("vasya");
        var res3 = base.findByPhone("9990");
        var res4 = base.findByName("sasha");

        //Assert
        assertThat(res1).isEmpty();
        assertThat(res2).contains(two);
        assertThat(res3).isEmpty();
        assertThat(res4).isEmpty();
    }


    @Test
    @DisplayName("ReadWriteLock позволяет избежать одновременной работы пишущих и читающих потоков и также связанных с этим исключений")
    void multiThread() {
        //Arrange
        var one = new Person(123,null,"spb","9900");
        var base = new Task3();

        //Act
        for (int h = 0; h < 10000; h++) {
            new Thread(()->base.add(one)).start();
            new Thread(()->base.delete(123)).start();
            new Thread(()->base.findByAddress("spb")).start();
        }

    }
}
