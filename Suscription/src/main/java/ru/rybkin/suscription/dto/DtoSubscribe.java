package ru.rybkin.suscription.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * dto - запроса на подписку
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoSubscribe {

    private String name;

    private String url;
}
