package ru.rybkin.subscriber.service;


import ru.rybkin.subscriber.dto.DtoMessage;
import ru.rybkin.subscriber.dto.DtoRequest;

public interface ServiceSubscriber {

    DtoMessage acceptMessage(DtoMessage dto);

    void subscribe(DtoRequest url);

    void unsubscribe(DtoRequest url);
}
