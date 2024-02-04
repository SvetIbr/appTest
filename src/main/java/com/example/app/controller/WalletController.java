package com.example.app.controller;

import com.example.app.dto.WalletDto;
import com.example.app.dto.WalletOperationDto;
import com.example.app.service.WalletOperationService;
import com.example.app.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Класс контроллера для работы с запросами к сервису кошельков и операциям по ним
 *
 * @author Светлана Ибраева
 * @version 1.0
 */
@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(path = "api/v1")
public class WalletController {
    /**
     * Поле сервис для работы с хранилищем кошельков
     */
    private final WalletService walletService;
    /**
     * Поле сервис для работы с хранилищем операций по кошелькам
     */
    private final WalletOperationService walletOperationService;

    /**
     * Метод добавления операции по кошельку в хранилище операций сервиса через запрос
     *
     * @param walletOperationDto {@link WalletOperationDto}
     * @return {@link WalletOperationDto} с добавленным id и код ответа API 200
     */
    @PostMapping("/wallet")
    @ResponseStatus(HttpStatus.OK)
    public WalletOperationDto createWal(@Validated @RequestBody WalletOperationDto walletOperationDto) {
        log.info("POST: создание операции с параметрами: " + walletOperationDto);
        return walletOperationService.create(walletOperationDto);
    }

    /**
     * Метод получения информации о конкретном кошельке из хранилища сервиса
     * по идентификатору через запрос
     *
     * @param walletId - идентификатор кошелька
     * @return {@link WalletDto}
     */
    @GetMapping("/wallets/{WALLET_UUID}")
    public WalletDto getWallets(@PathVariable("WALLET_UUID") String walletId) {
        log.info(String.format("GET: получение данных с параметрами: %s", walletId));
        return walletService.getById(walletId);
    }
}
