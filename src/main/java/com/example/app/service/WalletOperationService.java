package com.example.app.service;

import com.example.app.dto.WalletOperationDto;

/**
 * Интерфейс сервиса операций по кошельку
 *
 * @author Светлана Ибраева
 * @version 1.0
 */
public interface WalletOperationService {
    /**
     * Метод добавления операции по кошельку в хранилище
     *
     * @param walletOperationDto {@link WalletOperationDto}
     * @return {@link WalletOperationDto} с добавленным id и код ответа API 200
     */
    WalletOperationDto create(WalletOperationDto walletOperationDto);
}
