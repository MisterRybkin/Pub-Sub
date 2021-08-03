package ru.rybkin.purchase.service;

import ru.rybkin.purchase.dto.DtoSubscriber;

public interface ServicePurchase {

    void addSub(DtoSubscriber dto);

    void removeSub(String name);

    void notifySub();
}
