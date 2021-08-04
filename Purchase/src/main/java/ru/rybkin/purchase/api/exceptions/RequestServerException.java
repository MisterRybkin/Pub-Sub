package ru.rybkin.purchase.api.exceptions;

import ru.rybkin.purchase.dto.DtoMessage;

public class RequestServerException extends RuntimeException {
    public RequestServerException(String url, DtoMessage dtoMessage) {
        super("Client server error url: " + url + " dto: " + dtoMessage);
    }
}
