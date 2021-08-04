package ru.rybkin.subscriber.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.rybkin.subscriber.api.exception.NotFoundException;
import ru.rybkin.subscriber.api.exception.RequestClientException;
import ru.rybkin.subscriber.component.SendRequest;
import ru.rybkin.subscriber.dto.DtoMessage;
import ru.rybkin.subscriber.dto.DtoRequest;
import ru.rybkin.subscriber.dto.DtoSubscribe;
import ru.rybkin.subscriber.entity.Purchase;
import ru.rybkin.subscriber.entity.Subscriber;
import ru.rybkin.subscriber.repositories.RepoPurchase;
import ru.rybkin.subscriber.repositories.RepoSubscriber;
import ru.rybkin.subscriber.service.ServiceSubscriber;


@Slf4j
@Service
public class ServiceSubscriberImpl implements ServiceSubscriber {

    @Value("${value.home.url}")
    private String homeURL;

    @Value("${value.home.name}")
    private String name;

    private final RepoSubscriber repoSubscriber;
    private final RepoPurchase repoPurchase;
    private final SendRequest sendRequest;

    public ServiceSubscriberImpl(RepoSubscriber repoSubscriber,
                                 RepoPurchase repoPurchase,
                                 SendRequest sendRequest) {
        this.repoSubscriber = repoSubscriber;
        this.repoPurchase = repoPurchase;
        this.sendRequest = sendRequest;
    }

    @Override
    public DtoMessage acceptMessage(DtoMessage dto) {

        switch (dto.getAction().toUpperCase()) {
            case "SUBSCRIPTION":
                Subscriber subscriber = new Subscriber();
                subscriber.setMsisdn(dto.getMsisdn());
                subscriber.setTimestamp(dto.getTimestamp());
                subscriber = repoSubscriber.saveAndFlush(subscriber);
                dto.setId(subscriber.getId());
                log.debug("  _. save message with type: {}, id: {}", subscriber.getClass().getSimpleName(), subscriber.getId());
                return dto;
            case "PURCHASE":
                Purchase purchase = new Purchase();
                purchase.setMsisdn(dto.getMsisdn());
                purchase.setTimestamp(dto.getTimestamp());
                purchase = repoPurchase.saveAndFlush(purchase);
                dto.setId(purchase.getId());
                log.debug("  _. save message with type: {}, id: {}", purchase.getClass().getSimpleName(), purchase.getId());
                return dto;
            default:
                throw new NotFoundException(dto.getAction(), "action");
        }
    }

    @Override
    public void subscribe(DtoRequest dto) {

        DtoSubscribe dtoSubscribe = new DtoSubscribe(name, homeURL);

        sendRequest.post(dto.getUrl(), dtoSubscribe)
                .subscribe();
        log.debug(" _.insert name: {}, homeURL: {} on url: {}", name, homeURL, dto.getUrl());
    }

    @Override
    public void unsubscribe(DtoRequest dto) {

        try {
            sendRequest.delete(dto.getUrl(), name)
                    .subscribe();
        } catch (RequestClientException ex) {

        }

        log.debug(" _.has been sent: {}", dto.getUrl());
    }
}
