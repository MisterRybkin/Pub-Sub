package ru.rybkin.purchase.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/purchase")
public class ApiPurchase {

    /**
     * метод   для добавления новых Subscriptions
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<HttpStatus> addSubscription(String url) {
        return null;
    }


}
