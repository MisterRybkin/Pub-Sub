package ru.rybkin.subscriber.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rybkin.subscriber.dto.DtoMessage;
import ru.rybkin.subscriber.service.ServiceSubscriber;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Slf4j
@RestController
@Validated
@RequestMapping("api/subscriber")
public class ApiSubscriber {

    private final ServiceSubscriber serviceSubscriber;

    public ApiSubscriber(ServiceSubscriber serviceSubscriber) {
        this.serviceSubscriber = serviceSubscriber;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<HttpStatus> subscribe(@RequestBody @NotBlank String url) {
        log.info("-> subscribe on: {}", url);
        serviceSubscriber.subscribe(url);
        log.info("<- subscribe on: {}", url);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<HttpStatus> unsubscribe(@RequestBody @NotBlank String url) {
        log.info("-> unsubscribe on url: {}", url);
        serviceSubscriber.unsubscribe(url);
        log.info("<- unsubscribe on url: {}", url);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> acceptMessage(@Valid @RequestBody DtoMessage dto) {
        log.info("-> accept message: {}", dto.toString());
        serviceSubscriber.acceptMessage(dto);
        log.info("<- accept message: {}", dto.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}