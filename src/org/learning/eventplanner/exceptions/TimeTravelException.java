package org.learning.eventplanner.exceptions;

public class TimeTravelException extends Exception {
    public TimeTravelException() {
    }

    public TimeTravelException(String message) {
        super(message);
    }

    public TimeTravelException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeTravelException(Throwable cause) {
        super(cause);
    }

    public TimeTravelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
