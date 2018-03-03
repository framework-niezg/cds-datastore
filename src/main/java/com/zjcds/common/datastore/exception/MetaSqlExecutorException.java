package com.zjcds.common.datastore.exception;

/**
 * created date：2018-02-28
 * @author niezhegang
 */
public class MetaSqlExecutorException extends RuntimeException{

    public MetaSqlExecutorException() {
    }

    public MetaSqlExecutorException(String message) {
        super(message);
    }

    public MetaSqlExecutorException(String message, Throwable cause) {
        super(message, cause);
    }

    public MetaSqlExecutorException(Throwable cause) {
        super(cause);
    }

    public MetaSqlExecutorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
