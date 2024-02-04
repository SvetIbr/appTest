package com.example.app.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class WalletOperationDto {
    private Long id;

    @NotNull
    private String walletId;

    @NotNull
    private String operationType;

    @Min(value = 1)
    @NotNull
    private Long amount;

    private LocalDateTime created = LocalDateTime.now();
}
