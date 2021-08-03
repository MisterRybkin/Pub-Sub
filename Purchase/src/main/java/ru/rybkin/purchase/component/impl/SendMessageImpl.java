package ru.rybkin.purchase.component.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.rybkin.purchase.component.SendMessage;
import ru.rybkin.purchase.dto.DtoMessage;

@Slf4j
@Component
public class SendMessageImpl implements SendMessage {

    @Override
    public void send(String url, DtoMessage dtoMessage) {

        RestTemplate restTemplate = new RestTemplate();

        DtoMessage insertedDto = restTemplate.postForObject(url, dtoMessage, DtoMessage.class);
        log.debug(" _.insert dto: {}", insertedDto);
    }
}
