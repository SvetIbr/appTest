package com.example.app.service;

import com.example.app.dto.WalletDto;
import com.example.app.dto.WalletOperationDto;
import com.example.app.error.exception.BadRequestException;
import com.example.app.error.exception.InsufficientFundsException;
import com.example.app.error.exception.NotFoundException;
import com.example.app.mapper.WalletMapper;
import com.example.app.mapper.WalletOperationMapper;
import com.example.app.model.Wallet;
import com.example.app.model.WalletOperation;
import com.example.app.storage.WalletOperationRepository;
import com.example.app.storage.WalletRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.app.util.Constants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService{
    private final WalletRepository walletRepository;
    private final WalletOperationRepository walletOperationRepository;
    private final WalletOperationMapper walletOperationMapper;
    private final WalletMapper walletMapper;
    @Transactional
    public WalletOperationDto create(WalletOperationDto walletOperationDto) {
        Wallet wallet = walletRepository.findById(walletOperationDto.getWalletId()).orElseThrow
                (() -> new NotFoundException(String.format(WALLET_NOT_FOUND, walletOperationDto.getWalletId())));
        WalletOperation walletOperation = walletOperationMapper.toWalletOperation(walletOperationDto);
        switch (walletOperation.getOperationType()) {
            case DEPOSIT -> doDeposit(wallet, walletOperation.getAmount());
            case WITHDRAW -> doWithdraw(wallet, walletOperation.getAmount());
            default -> throw new BadRequestException(String.format(UNEXPECTED_VALUE_MSG,
                    walletOperation.getOperationType()));
        }

        return walletOperationMapper.toWalletOperationDto(walletOperationRepository.save(walletOperation));

    }

    private void doWithdraw(Wallet wallet, Long amount) {
        if (wallet.getBalance() < amount) {
            throw new InsufficientFundsException(INSUFFICIENT_FUNDS);
        } else {
            amount = - amount;
            doDeposit(wallet, amount);
        }
    }

    private void doDeposit(Wallet wallet, Long amount) {
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);
    }

    @Transactional(readOnly = true)
    public WalletDto getById(String walletId) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow
                (() -> new NotFoundException(String.format(WALLET_NOT_FOUND, walletId)));
      return walletMapper.toWalletDto(wallet);
    }
}
