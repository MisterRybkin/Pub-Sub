package ru.rybkin.subscriber.api.exception;


public class RequestServerException extends RuntimeException {
    public RequestServerException(String url) {
        super("Client server error url on url: " + url);
    }
}
