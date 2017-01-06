package com.wool.hermes.exception;

/**
 * Created by wanglin on 17-1-6.
 */
public class QueueMessageException extends RuntimeException {

    public QueueMessageException() {
        super();
    }

    public QueueMessageException(String message) {
        super(message);
    }
}
