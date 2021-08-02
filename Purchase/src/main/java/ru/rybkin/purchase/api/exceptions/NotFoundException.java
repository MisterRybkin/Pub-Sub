package ru.rybkin.purchase.api.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Integer id, String value) {
        super("Could not find [" + value + "] with id [" + id + "]");
    }

    public NotFoundException(String key, String value) {
        super("Could not find [" + value + "] with key [" + key + "]");
    }
}
