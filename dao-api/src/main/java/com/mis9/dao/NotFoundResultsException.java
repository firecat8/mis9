package com.mis9.dao;

/**
 *
 * @author gdimitrova
 */
public class NotFoundResultsException extends RuntimeException {

    private final static String MESSAGE = "Not found results.";

    public NotFoundResultsException() {
        super();
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
