package ru.rybkin.purchase.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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
