package ru.rybkin.purchase.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Список адресов подписчиков
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SubscriptionURL {

    @Id
    private Integer id;

    private String name;

    private String url;
}
