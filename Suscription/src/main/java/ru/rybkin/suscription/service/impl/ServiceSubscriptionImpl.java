package ru.rybkin.suscription.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.rybkin.suscription.api.exception.NotFoundException;
import ru.rybkin.suscription.dto.DtoMessage;
import ru.rybkin.suscription.dto.DtoSubscribe;
import ru.rybkin.suscription.entity.Purchase;
import ru.rybkin.suscription.entity.Subscription;
import ru.rybkin.suscription.repositories.RepoPurchase;
import ru.rybkin.suscription.repositories.RepoSubscription;
import ru.rybkin.suscription.service.ServiceSubscription;

@Slf4j
@Service
public class ServiceSubscriptionImpl implements ServiceSubscription {

    @Value("${value.home.url}")
    private String homeURL;

    @Value("${value.home.name}")
    private String name;

    private final RepoSubscription repoSubscription;
    private final RepoPurchase repoPurchase;

    public ServiceSubscriptionImpl(RepoSubscription repoSubscription,
                                   RepoPurchase repoPurchase) {
        this.repoSubscription = repoSubscription;
        this.repoPurchase = repoPurchase;
    }

    @Override
    public void acceptMessage(DtoMessage dto) {

        switch (dto.getAction().toUpperCase()) {
            case "SUBSCRIPTION":
                Subscription subscription = new Subscription();
                subscription.setMsisdn(dto.getMsisdn());
                subscription.setTimestamp(dto.getTimestamp());
                subscription = repoSubscription.saveAndFlush(subscription);
                log.debug("  _. save message with type: {}, id: {}", subscription.getClass().getSimpleName(), subscription.getId());
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

        DtoSubscribe dtoSubscribe = new DtoSubscribe(name, homeURL);

        String insertedURL = restTemplate.postForObject(url, dtoSubscribe, String.class);
        log.debug(" _.insert url: {}", insertedURL);
    }

    @Override
    public void unsubscribe(String url) {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getForObject(url, String.class);
        log.debug(" _.has been sent: {}", url);
    }
}
