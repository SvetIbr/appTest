package com.example.app.service;

import com.example.app.dto.WalletDto;
import com.example.app.dto.WalletOperationDto;
import com.example.app.model.WalletOperation;

import java.util.List;

public interface WalletService {
    WalletOperationDto create(WalletOperationDto walletOperationDto);

    WalletDto getById(String walletId);
}
