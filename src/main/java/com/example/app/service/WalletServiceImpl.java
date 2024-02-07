package com.example.app.service;

import com.example.app.error.exception.NotFoundException;
import com.example.app.model.Wallet;
import com.example.app.storage.WalletRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.app.util.Constants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    @Transactional(readOnly = true)
    public Wallet getById(String walletId) {
        if (walletId == null) {
            throw new NotFoundException(ID_IS_EMPTY);
        }
        return walletRepository.getByIdforRead(walletId).orElseThrow
                (() -> new NotFoundException(String.format(WALLET_NOT_FOUND, walletId)));
    }
}
