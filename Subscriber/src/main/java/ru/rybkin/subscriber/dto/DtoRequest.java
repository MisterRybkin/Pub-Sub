package ru.rybkin.subscriber.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoRequest {

    private String url;

    @Override
    public String toString() {
        return "DtoRequest{" +
                "url='" + url + '\'' +
                '}';
    }
}
