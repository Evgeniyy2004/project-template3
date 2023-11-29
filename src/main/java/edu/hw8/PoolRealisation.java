package edu.hw8;

public class PoolRealisation implements ThreadPool {

    volatile Thread[] all = new Thread[0];

    public void create(int threads) {
        all = new Thread[threads];
    }

    @Override
    public void execute(Runnable runnable) {
        for (int i = 0; i < all.length; i++) {
            if (i == all.length) {
                i = 0;
            }
            if (all[i] == null) {
                all[i] = new Thread(runnable);
                try {
                    all[i].join();
                    all[i] = null;
                    break;
                } catch (InterruptedException e) {
                }
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
