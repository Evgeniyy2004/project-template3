package edu.hw2;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(int maxAttempts, boolean isFaulty) {
        if (maxAttempts < 0) {
            throw new IllegalArgumentException("Количество попыток не может быть отрицательным.");
        }
        this.maxAttempts = maxAttempts;
        if (!isFaulty) {
            manager = new DefaultConnectionManager();
        } else {
            manager = new FaultyConnectionManager();
        }
    }


    public void updatePackages() throws ConnectionException {
        try {
            tryExecute("apt update && apt upgrade -y");
        }  catch (ConnectionException e) {
            throw e;
        }
    }

    void tryExecute(String command) throws ConnectionException {
        ConnectionException  exception = null;
        var currConnection = manager.getConnection();
        for (int i = 0; i < maxAttempts; i++) {
                try {
                    currConnection.execute(command);
                    return;
                } catch (ConnectionException newException) {
                    exception = newException;
                }
            }

            throw new ConnectionException("Ошибка", exception);
    }
}
