package ru.rybkin.purchase.api.exceptions;

import ru.rybkin.purchase.dto.DtoMessage;

public class RequestClientException extends RuntimeException {

    public RequestClientException(String url, DtoMessage dto) {
        super("Api error, check your request: " + url + " dto: " + dto);
    }
}
