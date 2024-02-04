package com.example.app.dto;

import lombok.*;

/**
 * Класс кошелька со свойствами <b>id</b> и <b>balance</b> для работы через REST-интерфейс
 *
 * @author Светлана Ибраева
 * @version 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletDto {
    /**
     * Поле идентификатор
     */
    private String id;

    /**
     * Поле баланс
     */
    private long balance;
}
