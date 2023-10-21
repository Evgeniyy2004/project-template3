package edu.hw2;

public class ConnectionException extends RuntimeException {
    public final String messageOf;
    public final Exception reason;

    public ConnectionException() {
        messageOf = "Ошибка";
        reason = null;
    }

    public ConnectionException(String message, Exception cause) {
        this.messageOf = message;
        this.reason = cause;
    }
}
