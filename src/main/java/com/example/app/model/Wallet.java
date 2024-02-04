package com.example.app.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

/**
 * Класс кошелька со свойствами <b>id</b> и <b>balance</b> для работы с базой данных
 *
 * @author Светлана Ибраева
 * @version 1.0
 */
@Entity
@Table(name = "wallet")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Wallet {
    /**
     * Поле идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * Поле баланс
     */
    private long balance;
}
