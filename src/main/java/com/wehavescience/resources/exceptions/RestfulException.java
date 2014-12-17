package com.wehavescience.resources.exceptions;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class RestfulException extends RuntimeException{
    private int statusCode;

    public RestfulException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public RestfulException(int statusCode, String message, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
