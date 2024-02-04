package com.example.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Класс операции по кошельку со свойствами <b>id</b>, <b>walletId</b>, <b>operationType</b>,
 * <b>amount</b> и <b>created</b> для работы с базой данных
 *
 * @author Светлана Ибраева
 * @version 1.0
 */
@Entity
@Table(name = "operation")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class WalletOperation {
    /**
     * Поле идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Поле идентификатор кошелька
     */
    @Column(name = "wallet_id")
    @NotNull(message = "WalletId should not be null")
    private String walletId;

    /**
     * Поле тип операции
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NotNull(message = "OperationType should not be null")
    private OperationType operationType;

    /**
     * Поле сумма
     */
    @Min(value = 1, message = "Amount must be greater than zero")
    @NotNull(message = "Amount should not be null")
    private Long amount;

    /**
     * Поле дата и время создания операции
     */
    private String created;
}
