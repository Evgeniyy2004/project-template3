package edu.hw2;

public class ConnectionException extends RuntimeException {
    public String messageOf;
    public Exception reason;

    public ConnectionException() {
    }

    public ConnectionException(String message, Exception cause) {
        this.messageOf = message;
        this.reason = cause;
    }
}
