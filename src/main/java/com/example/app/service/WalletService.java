package com.example.app.service;

import com.example.app.dto.WalletDto;
import com.example.app.model.Wallet;

/**
 * Интерфейс сервиса кошельков
 *
 * @author Светлана Ибраева
 * @version 1.0
 */
public interface WalletService {
    /**
     * Метод получения информации о конкретном кошельке из хранилища по идентификатору
     *
     * @param walletId - идентификатор кошелька
     * @return {@link WalletDto}
     */
    Wallet getById(String walletId);
}
