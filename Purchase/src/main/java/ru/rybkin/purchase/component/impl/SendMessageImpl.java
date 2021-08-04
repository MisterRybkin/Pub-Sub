package ru.rybkin.purchase.component.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.rybkin.purchase.api.exceptions.RequestClientException;
import ru.rybkin.purchase.api.exceptions.RequestServerException;
import ru.rybkin.purchase.component.SendMessage;
import ru.rybkin.purchase.dto.DtoMessage;

@Slf4j
@Component
public class SendMessageImpl implements SendMessage {

    @Override
    public Mono<DtoMessage> send(String url, DtoMessage dtoMessage) {
        WebClient client = WebClient.create();

        return client.post()
                .uri(url)
                .body(Mono.just(dtoMessage), DtoMessage.class)
                .retrieve()
                    .onStatus(HttpStatus::is4xxClientError,
                            error -> Mono.error(new RequestClientException(url, dtoMessage)))
                    .onStatus(HttpStatus::is5xxServerError,
                            error -> Mono.error(new RequestServerException(url, dtoMessage)))
                .bodyToMono(DtoMessage.class);
    }
}
