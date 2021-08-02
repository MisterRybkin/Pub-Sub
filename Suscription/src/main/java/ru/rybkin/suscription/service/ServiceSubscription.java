package ru.rybkin.suscription.service;

import ru.rybkin.suscription.dto.DtoMessage;

public interface ServiceSubscription {

    void acceptMessage(DtoMessage dto);
}
