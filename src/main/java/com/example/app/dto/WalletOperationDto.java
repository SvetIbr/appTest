package com.example.app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 36, max = 36)
    private String walletId;

    @NotNull
    private String operationType;

    @Min(value = 1)
    @NotNull
    private Long amount;

    private LocalDateTime created = LocalDateTime.now();
}
