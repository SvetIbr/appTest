package com.example.app.service;

import com.example.app.dto.WalletDto;

public interface WalletService {
    WalletDto getById(String walletId);
}
