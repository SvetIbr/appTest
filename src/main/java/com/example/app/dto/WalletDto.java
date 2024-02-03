package com.example.app.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletDto {
    private String id;
    private long balance;
}
