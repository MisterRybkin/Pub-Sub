package ru.rybkin.subscriber.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.rybkin.subscriber.api.exception.NotFoundException;
import ru.rybkin.subscriber.dto.DtoMessage;
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

    public ServiceSubscriberImpl(RepoSubscriber repoSubscriber,
                                 RepoPurchase repoPurchase) {
        this.repoSubscriber = repoSubscriber;
        this.repoPurchase = repoPurchase;
    }

    @Override
    public void acceptMessage(DtoMessage dto) {

        switch (dto.getAction().toUpperCase()) {
            case "SUBSCRIPTION":
                Subscriber subscriber = new Subscriber();
                subscriber.setMsisdn(dto.getMsisdn());
                subscriber.setTimestamp(dto.getTimestamp());
                subscriber = repoSubscriber.saveAndFlush(subscriber);
                log.debug("  _. save message with type: {}, id: {}", subscriber.getClass().getSimpleName(), subscriber.getId());
                break;
            case "PURCHASE":
                Purchase purchase = new Purchase();
                purchase.setMsisdn(dto.getMsisdn());
                purchase.setTimestamp(dto.getTimestamp());
                purchase = repoPurchase.saveAndFlush(purchase);
                log.debug("  _. save message with type: {}, id: {}", purchase.getClass().getSimpleName(), purchase.getId());
                break;
            default:
                throw new NotFoundException(dto.getAction(), "action");
        }
    }

    @Override
    public void subscribe(String url) {
        RestTemplate restTemplate = new RestTemplate();

        DtoSubscribe dtoSubscribe = new DtoSubscribe("name", "homeURL");

        DtoSubscribe insertedDto = restTemplate.postForObject(url, dtoSubscribe, DtoSubscribe.class);
        log.debug(" _.insert name: {}, url: {} on url: {}", insertedDto.getName(), insertedDto.getUrl(), url);
    }

    @Override
    public void unsubscribe(String url) {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getForObject(url, String.class);
        log.debug(" _.has been sent: {}", url);
    }
}
