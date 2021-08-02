package ru.rybkin.suscription.service.impl;

import org.springframework.stereotype.Service;
import ru.rybkin.suscription.api.exception.NotFoundException;
import ru.rybkin.suscription.dto.DtoMessage;
import ru.rybkin.suscription.entity.Purchase;
import ru.rybkin.suscription.entity.Subscription;
import ru.rybkin.suscription.repositories.RepoPurchase;
import ru.rybkin.suscription.repositories.RepoSubscription;
import ru.rybkin.suscription.service.ServiceSubscription;

@Service
public class ServiceSubscriptionImpl implements ServiceSubscription {

    private final RepoSubscription repoSubscription;
    private final RepoPurchase repoPurchase;

    public ServiceSubscriptionImpl(RepoSubscription repoSubscription,
                                   RepoPurchase repoPurchase) {
        this.repoSubscription = repoSubscription;
        this.repoPurchase = repoPurchase;
    }

    @Override
    public void acceptMessage(DtoMessage dto) {

        switch (dto.getAction().toLowerCase()) {
            case "SUBSCRIPTION":
                Subscription subscription = new Subscription();
                subscription.setMsisdn(dto.getMsisdn());
                subscription.setTimestamp(dto.getTimestamp());
                repoSubscription.saveAndFlush(subscription);
                break;
            case "PURCHASE":
                Purchase purchase = new Purchase();
                purchase.setMsisdn(dto.getMsisdn());
                purchase.setTimestamp(dto.getTimestamp());
                repoPurchase.saveAndFlush(purchase);
                break;
            default:
                throw new NotFoundException(dto.getAction(), "action");
        }
    }

}
