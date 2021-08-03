package ru.rybkin.purchase.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rybkin.purchase.api.exceptions.NotFoundException;
import ru.rybkin.purchase.component.GenerateMessage;
import ru.rybkin.purchase.component.SendMessage;
import ru.rybkin.purchase.dto.DtoMessage;
import ru.rybkin.purchase.dto.DtoSubscriber;
import ru.rybkin.purchase.entities.SubscriptionURL;
import ru.rybkin.purchase.repositories.RepoSubscriptionURL;
import ru.rybkin.purchase.service.ServicePurchase;

import java.util.List;

@Slf4j
@Service
public class ServicePurchaseImpl implements ServicePurchase {

    private final RepoSubscriptionURL repoSubscriptionURL;
    private final GenerateMessage generateMessage;
    private final SendMessage sendMessage;

    public ServicePurchaseImpl(RepoSubscriptionURL repoSubscriptionURL,
                               GenerateMessage generateMessage,
                               SendMessage sendMessage) {
        this.repoSubscriptionURL = repoSubscriptionURL;
        this.generateMessage = generateMessage;
        this.sendMessage = sendMessage;
    }

    @Override
    public void addSub(DtoSubscriber dto) {
        SubscriptionURL entity = new SubscriptionURL();

        if (repoSubscriptionURL.existsByName(dto.getName())) {
            entity = repoSubscriptionURL.getByName(dto.getName());
            entity.setUrl(dto.getUrl());
        } else
            entity.setName(dto.getName());
        entity.setUrl(dto.getUrl());

        entity = repoSubscriptionURL.saveAndFlush(entity);
        log.debug("  _. add new Subscription with id: {}", entity.getId());
    }

    @Override
    public void removeSub(String name) {

        if (repoSubscriptionURL.existsByName(name)) {
            repoSubscriptionURL.deleteByName(name);
        } else {
            throw new NotFoundException(name, SubscriptionURL.class.getName());
        }
    }

    /**
     * отправить сообщения
     */

    @Override
    public void notifySub() {
        List<SubscriptionURL> urlList = repoSubscriptionURL.findAll();

        if (!urlList.isEmpty()) {
            for (SubscriptionURL sub:urlList
                 ) {
                DtoMessage dto = generateMessage.generate();
                sendMessage.send(sub.getUrl(),dto);
                log.debug(" _. successful sending message");
            }
        }
//        DtoMessage dto = generateMessage.generate();
//        sendMessage.send("http://localhost:8090/api/subscriber/", dto);
        //после каждой с url
    }
}
