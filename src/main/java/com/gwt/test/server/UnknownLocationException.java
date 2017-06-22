package com.gwt.test.server;

public class UnknownLocationException extends RuntimeException {
    public UnknownLocationException(String city) {
        super(city);
    }
}
