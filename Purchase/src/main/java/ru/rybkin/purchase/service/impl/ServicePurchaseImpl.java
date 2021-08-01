package ru.rybkin.purchase.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rybkin.purchase.entities.SubscriptionURL;
import ru.rybkin.purchase.repositories.RepoSubscriptionURL;
import ru.rybkin.purchase.service.ServicePurchase;

@Slf4j
@Service
public class ServicePurchaseImpl implements ServicePurchase {

    private final RepoSubscriptionURL repoSubscriptionURL;

    public ServicePurchaseImpl(RepoSubscriptionURL repoSubscriptionURL) {
        this.repoSubscriptionURL = repoSubscriptionURL;
    }

    @Override
    public void addSub(String name, String url) {
        SubscriptionURL entity = new SubscriptionURL();

//        entity.set
//
//        repoSubscriptionURL.save();
    }

    @Override
    public void updateSub(String name, String url) {
    }

    @Override
    public void removeSub(String name) {
    }

    @Override
    public void notifySub() {

    }
}
