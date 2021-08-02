package ru.rybkin.purchase.component.impl;

import org.springframework.stereotype.Component;
import ru.rybkin.purchase.component.GenerateMessage;
import ru.rybkin.purchase.dto.DtoMessage;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class GenerateMessageImpl implements GenerateMessage {

    @Override
    public DtoMessage generate() {
        DtoMessage dto = new DtoMessage();

        dto.setMsisdn((int) (Math.random() * 10000));
        dto.setAction(Action.getRandom().toString());
        dto.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));

        return dto;
    }

    public enum Action {
        PURCHASE,
        SUBSCRIPTION;

        public static Action getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }


}
