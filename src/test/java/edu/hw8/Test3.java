package edu.hw8;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class Test3 {

    @Test
    @DisplayName("Тест генерирует случайное количество паролей и имен, хэширует пароли с md5")
    void check() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //Arrange
        var n = new Random().nextInt(1000,2000);
        var users = new HashMap<String, String >();
        Brute.brute(users, n,"");
        var copy = (HashMap<String, String>) users.clone();

        //Act
        var start = System.currentTimeMillis();
        var res = Task3.multiThreadHacker(users);
        var dur = System.currentTimeMillis()-start;
        var start1 = System.currentTimeMillis();
        OneThreadPasswords.hacker(copy);
        var dur1 = System.currentTimeMillis()-start1;
        log.info("Однопоточно:"+(dur1));
        log.info("Многопоточно:"+(dur));

        //Assert
        assertThat(res.size()).isLessThanOrEqualTo(users.size());
        assertThat(dur).isLessThanOrEqualTo(dur1);
        for (var c : res.keySet()) {
            log.info(String.format("Пользователь: %s, Пароль: %s", res.get(c),c));
        }
    }
}
