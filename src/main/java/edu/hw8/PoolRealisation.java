package edu.hw8;

public class PoolRealisation implements ThreadPool {

    Thread[] all = new Thread[0];

    public void create(int threads) {

        all = new Thread[threads];
        for (int j =0; j < threads; j++) all[j] = new Thread();
    }

    @Override
    public void execute(Runnable runnable) {
        for (int i = 0; i < all.length; i++) {
            if (i == all.length) {
                i = 0;
            }
            if (!all[i].isAlive()) {
                all[i] = new Thread(runnable);
                all[i].run();
                break;
            }
        }

    }

    @Override
    public void close() throws Exception {
        for (int y = 0; y < all.length; y++) {
            if (all[y] != null) {
                all[y].interrupt();
            }
        }
    }
}
