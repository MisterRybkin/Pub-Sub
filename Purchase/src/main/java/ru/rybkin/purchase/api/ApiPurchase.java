package ru.rybkin.purchase.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rybkin.purchase.dto.DtoSubscriber;
import ru.rybkin.purchase.service.ServicePurchase;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Slf4j
@RestController
@Validated
@RequestMapping("api/purchase")
public class ApiPurchase {

    private final ServicePurchase servicePurchase;

    public ApiPurchase(ServicePurchase servicePurchase) {
        this.servicePurchase = servicePurchase;
    }

    /**
     * метод для добавления новых Subscriptions
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<HttpStatus> addSub(@Valid @RequestBody DtoSubscriber dto) {
        log.info("-> add: Subscription name: {}, url: {}", dto.getName(), dto.getUrl());
        servicePurchase.addSub(dto);
        log.info("<- add: Subscription name: {}, url: {}", dto.getName(), dto.getUrl());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * удалить подписчика
     * @param name имя подписчика и адрес
     * @return
     */
    @DeleteMapping("/{name}")
    public ResponseEntity<HttpStatus> removeSub(@PathVariable @NotBlank String name) {
        log.info("-> remove: Subscription name: {}", name);
        servicePurchase.removeSub(name);
        log.info("<- remove: Subscription name: {}", name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * сгенерировать сообщение для подписчиков
     * @return
     */
    @GetMapping("/notify")
    public ResponseEntity<HttpStatus> notifySub() {
        log.info("-> notify: Subscription");
        servicePurchase.notifySub();
        log.info("<- notify: Subscription");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
