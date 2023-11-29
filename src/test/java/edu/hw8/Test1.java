package edu.hw8;

import java.io.IOException;
import java.net.Socket;
import org.junit.jupiter.api.Test;

public class Test1 {

    @Test
    void checkServer() throws IOException, InterruptedException {
        new Thread(()->new Server().run()).start();
        var spcket = new Socket("localhost",49001);
        var spcket2 = new Socket("localhost",49001);
        Thread.sleep(1000*60);
    }
}
