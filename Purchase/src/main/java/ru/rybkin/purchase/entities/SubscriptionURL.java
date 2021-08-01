package ru.rybkin.purchase.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Список адресов подписчиков
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscription_url")
public class SubscriptionURL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * название подсписчика
     */
    @Column(unique = true)
    private String name;

    /**
     * url подписчика
     */
    @Column(unique = true)
    private String url;
}
