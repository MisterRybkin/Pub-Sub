package ru.rybkin.subscriber.component;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.rybkin.subscriber.api.exception.RequestClientException;
import ru.rybkin.subscriber.api.exception.RequestServerException;
import ru.rybkin.subscriber.dto.DtoSubscribe;

@Component
public class SendRequest {

    public Mono<Void> post(String url, DtoSubscribe dto) {
        WebClient client = WebClient.create();

        return client.post()
                .uri(url)
                .body(Mono.just(dto), DtoSubscribe.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RequestClientException(url)))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new RequestServerException(url)))
                .bodyToMono(Void.class);

    }

    public Mono<Void> delete(String url, String name) {
        WebClient client = WebClient.create();

        return client.delete()
                .uri(url + name)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RequestClientException(url)))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new RequestServerException(url)))
                .bodyToMono(Void.class);

    }
}
