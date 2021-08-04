package ru.rybkin.purchase.component.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.rybkin.purchase.component.SendMessage;
import ru.rybkin.purchase.dto.DtoMessage;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

@Slf4j
@Component
@AllArgsConstructor
public class SendMessageImpl implements SendMessage {

    @Override
    public Mono<DtoMessage> send(String url, DtoMessage dtoMessage) {
        WebClient client = WebClient.create();

        return client.post()
                .uri(url)
                .body(Mono.just(dtoMessage), DtoMessage.class)
                .retrieve()
                .bodyToMono(DtoMessage.class);

    }
}
