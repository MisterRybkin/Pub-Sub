package ru.rybkin.subscriber.api.exception;


public class RequestClientException extends RuntimeException {

    public RequestClientException(String url) {
        super("Api error, check your request on url: " + url);
    }
}
