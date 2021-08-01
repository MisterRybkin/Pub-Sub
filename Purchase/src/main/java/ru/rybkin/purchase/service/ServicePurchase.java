package ru.rybkin.purchase.service;

public interface ServicePurchase {

    void addSub(String name, String url);

    void updateSub(String name, String url);

    void removeSub(String name);

    void notifySub();
}
