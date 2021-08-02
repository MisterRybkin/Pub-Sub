package ru.rybkin.suscription.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rybkin.suscription.dto.DtoMessage;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/subscription")
public class ApiSubscription {

    @PostMapping("/")
    public ResponseEntity<HttpStatus> acceptMessage(@Valid @RequestBody DtoMessage dto) {
        log.info("-> accept message: {}", dto.toString());

        log.info("<- accept message: {}", dto.toString());
        return new ResponseEntity(HttpStatus.OK);
    }
}
