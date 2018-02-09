package com.zjcds.common.datastore.exception;

/**
 * created date：2017-08-05
 * @author niezhegang
 */
public class ConnectionFailException extends RuntimeException{

    public ConnectionFailException() {
        super();
    }

    public ConnectionFailException(String message) {
        super(message);
    }

    public ConnectionFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionFailException(Throwable cause) {
        super(cause);
    }

    protected ConnectionFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
