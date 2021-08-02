package ru.rybkin.suscription.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoMessage {

    @Positive
    private Integer id;

    /**
     * уникальный номер абонента
     */
    @Positive
    private Integer msisdn;

    /**
     * тип сообщения PURCHASE или SUBSCRIPTION
     */
    @NotBlank
    private String action;

    /**
     * UNIX timestamp
     */
    private Timestamp timestamp;

    @Override
    public String toString() {
        return "DtoMessage{" +
                "id=" + id +
                ", msisdn=" + msisdn +
                ", action='" + action + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
