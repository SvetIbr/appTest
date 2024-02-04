package com.example.app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Класс операции по кошельку со свойствами <b>id</b>, <b>walletId</b>, <b>operationType</b>,
 * <b>amount</b> и <b>created</b> для работы через REST-интерфейс
 *
 * @author Светлана Ибраева
 * @version 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class WalletOperationDto {
    /**
     * Поле идентификатор
     */
    private Long id;

    /**
     * Поле идентификатор кошелька
     */
    @NotNull(message = "WalletId should not be null")
    @Size(min = 36, max = 36, message = "WalletId must be 36 characters long")
    private String walletId;

    /**
     * Поле тип операции
     */
    @NotNull(message = "OperationType should not be null")
    private String operationType;

    /**
     * Поле сумма
     */
    @Min(value = 1, message = "Amount must be greater than zero")
    @NotNull(message = "Amount should not be null")
    private Long amount;

    /**
     * Поле дата и время создания операции
     */
    private LocalDateTime created = LocalDateTime.now();
}
