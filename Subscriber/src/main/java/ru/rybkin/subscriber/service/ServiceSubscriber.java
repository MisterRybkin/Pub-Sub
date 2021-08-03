package ru.rybkin.subscriber.service;


import ru.rybkin.subscriber.dto.DtoMessage;

public interface ServiceSubscriber {

    void acceptMessage(DtoMessage dto);

    void subscribe(String url);

    void unsubscribe(String url);
}
