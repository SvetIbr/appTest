package com.example.app.service;

import com.example.app.error.exception.BadRequestException;
import com.example.app.error.exception.InsufficientFundsException;
import com.example.app.error.exception.NotFoundException;
import com.example.app.model.WalletOperation;
import com.example.app.storage.WalletOperationRepository;
import com.example.app.storage.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import static com.example.app.util.Constants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletOperationServiceImpl implements WalletOperationService {
    private final WalletRepository walletRepository;
    private final WalletOperationRepository walletOperationRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(WalletOperation walletOperation) {
        if (walletOperation == null) {
            throw new BadRequestException(IS_EMPTY);
        }
        walletRepository.findById(walletOperation.getWalletId()).ifPresentOrElse(wallet -> {
            switch (walletOperation.getOperationType()) {
                case DEPOSIT -> wallet.setBalance(wallet.getBalance() + walletOperation.getAmount());
                case WITHDRAW -> {
                    if (wallet.getBalance() < walletOperation.getAmount()) {
                        throw new InsufficientFundsException(INSUFFICIENT_FUNDS);
                    }
                    wallet.setBalance(wallet.getBalance() - walletOperation.getAmount());
                }
                default -> throw new BadRequestException(String.format(UNEXPECTED_VALUE_MSG,
                        walletOperation.getOperationType()));
            }
        }, () -> {
            throw new NotFoundException(String.format(WALLET_NOT_FOUND,
                    walletOperation.getWalletId()));
        });

        walletOperationRepository.save(walletOperation);

    }
}
