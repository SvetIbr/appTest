package com.example.app.service;

import com.example.app.model.WalletOperation;

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
     * @param walletOperation {@link WalletOperation}
     */
    void create(WalletOperation walletOperation);
}
