package ru.rybkin.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoMessage {

    private Integer id;

    /**
     * уникальный номер абонента
     */
    private Integer msisdn;

    /**
     * тип сообщения PURCHASE или SUBSCRIPTION
     */
    private String action;

    /**
     * UNIX timestamp
     */
    private Timestamp timestamp; //TODO: Как передвать timestamp?
}
