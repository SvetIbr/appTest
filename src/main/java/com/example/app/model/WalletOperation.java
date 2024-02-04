package com.example.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "operation")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class WalletOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wallet_id")
    @NotNull
    private String walletId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NotNull
    private OperationType operationType;

    @Min(value = 1)
    @NotNull
    private Long amount;

    private String created;
}
