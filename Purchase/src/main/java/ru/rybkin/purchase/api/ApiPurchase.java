package ru.rybkin.purchase.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rybkin.purchase.service.ServicePurchase;

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
    public ResponseEntity<HttpStatus> addSub(@RequestBody @NotBlank String name,
                                             @RequestBody @NotBlank String url) {
        log.info("-> add: Subscription name: {}, url: {}", name, url);
        servicePurchase.addSub(name, url);
        log.info("<- add: Subscription name: {}, url: {}", name, url);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * метод для обновления url Subscriptions
     * @return
     */
    @PutMapping("/{name}")
    public ResponseEntity<HttpStatus> updateSub(@PathVariable String name,
                                                @RequestBody @NotBlank String url) {
        log.info("-> update: Subscription name: {}, url: {}", name, url);
        servicePurchase.updateSub(name, url);
        log.info("<- update: Subscription name: {}, url: {}", name, url);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * удалить подписчика
     * @param name имя подписчика
     * @return
     */
    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> removeSub(@RequestBody @NotBlank String name) {
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
