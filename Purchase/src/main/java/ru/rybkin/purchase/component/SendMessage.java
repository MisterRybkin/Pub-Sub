package ru.rybkin.purchase.component;

import ru.rybkin.purchase.dto.DtoMessage;

public interface SendMessage {

    void send(String url, DtoMessage dtoMessage);
}
