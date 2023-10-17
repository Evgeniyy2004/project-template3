package hw2;

import edu.hw2.ConnectionException;
import edu.hw2.DefaultConnectionManager;
import edu.hw2.FaultyConnection;
import edu.hw2.FaultyConnectionManager;
import edu.hw2.PopularCommandExecutor;
import edu.hw2.StableConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test3 {

        @Test
        @DisplayName("StableConnection всегда работает без ошибок, а FaultyConnection в половине попыток, включая первую, возвращает исключение")
        void notFaultyAndFaulty()  {
            StableConnection st = new StableConnection();
            for(int i = 0; i < 1000; i++) {
                try {
                    st.execute("Do it again");
                    st.close();
                } catch (ConnectionException notConnect) {
                    assertThat(false).isTrue();
                }
            }

            FaultyConnection fc = new FaultyConnection();
            for(int i = 0; i < 1000; i++) {
                if((i + 1)% 2 !=0) {
                    try {
                        fc.execute("Error please");
                        assertThat(false).isTrue();
                    } catch (ConnectionException notConnect) {
                        assertThat(true).isTrue();
                    }
                } else {
                    try {
                        fc.execute("No Errors");
                        fc.close();
                    } catch (ConnectionException notConnect) {
                        assertThat(false).isTrue();
                    }
                }
            }

            st.close();
            fc.close();
        }

        @Test
        @DisplayName("DefaultConnectionManager на каждой нечетной попытке возвращает FaultyConnection, FaultyConnectionManager - на каждой попытке")
        void DefaultAndFaultManager()  {
        DefaultConnectionManager dm = new DefaultConnectionManager();
        for(int i = 0; i< 1000; i++) {
            if((i + 1) % 2 != 0) {
                try {
                    var c = (FaultyConnection) dm.getConnection();
                    c.close();
                } catch (ClassCastException notCast) {
                    assertThat(false).isTrue();
                }
            } else {
                try {
                    var c = (StableConnection) dm.getConnection();
                    c.close();
                } catch (ClassCastException notCast) {
                    assertThat(false).isTrue();
                }
            }
        }

        FaultyConnectionManager fm = new FaultyConnectionManager();
        for(int j = 0; j < 1000; j++){
            try {
                var c = (FaultyConnection) fm.getConnection();
                c.close();
            } catch (ClassCastException notCast) {
                assertThat(false).isTrue();
            }
        }
    }

    @Test
    @DisplayName("Метод tryExecute либо выполняет заданную команду за заданное количество попыток, либо возвращает ConnectionException")
    void tryExecuteTest()  {

            //За 1 попытку команда будет выполнена только если параметра isFaulty равен false
            var first = new PopularCommandExecutor(1 , false);
            var second = new PopularCommandExecutor(1, true);
            try {
                first.updatePackages();
                assertThat(false).isTrue();
            } catch( ConnectionException a) {
                assertThat(true).isTrue();
            }

            try {
                second.updatePackages();
                assertThat(false).isTrue();
            }  catch( ConnectionException a) {
                    assertThat(true).isTrue();
            }

            //За 2 и более попытки команда будет гарантированно выполнена
            first = new PopularCommandExecutor(3 , false);
            second = new PopularCommandExecutor(7 , true);

        try {
            first.updatePackages();
            assertThat(true).isTrue();
        } catch( ConnectionException a) {
            assertThat(false).isTrue();
        }

        try {
            second.updatePackages();
            assertThat(true).isTrue();
        }  catch( ConnectionException a) {
            assertThat(false).isTrue();
        }
    }

}
