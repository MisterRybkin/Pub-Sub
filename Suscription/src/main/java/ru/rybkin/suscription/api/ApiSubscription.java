package ru.rybkin.suscription.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rybkin.suscription.dto.DtoMessage;
import ru.rybkin.suscription.service.ServiceSubscription;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Slf4j
@RestController
@Validated
@RequestMapping("api/subscription")
public class ApiSubscription {

    private final ServiceSubscription serviceSubscription;

    public ApiSubscription(ServiceSubscription serviceSubscription) {
        this.serviceSubscription = serviceSubscription;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<HttpStatus> subscribe(@RequestBody @NotBlank String url) {
        log.info("-> subscribe on: {}", url);
        serviceSubscription.subscribe(url);
        log.info("<- subscribe on: {}", url);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<HttpStatus> unsubscribe(@RequestBody @NotBlank String url) {
        log.info("-> unsubscribe on url: {}", url);
        serviceSubscription.unsubscribe(url);
        log.info("<- unsubscribe on url: {}", url);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> acceptMessage(@Valid @RequestBody DtoMessage dto) {
        log.info("-> accept message: {}", dto.toString());
        serviceSubscription.acceptMessage(dto);
        log.info("<- accept message: {}", dto.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
