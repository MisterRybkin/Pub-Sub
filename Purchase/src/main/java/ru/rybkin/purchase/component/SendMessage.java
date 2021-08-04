package ru.rybkin.purchase.component;

import reactor.core.publisher.Mono;
import ru.rybkin.purchase.dto.DtoMessage;

public interface SendMessage {

    Mono<DtoMessage> send(String url, DtoMessage dtoMessage);
}
