package com.example.app.controller;

import com.example.app.dto.WalletDto;
import com.example.app.dto.WalletOperationDto;
import com.example.app.mapper.WalletMapper;
import com.example.app.mapper.WalletOperationMapper;
import com.example.app.model.WalletOperation;
import com.example.app.service.WalletOperationServiceImpl;
import com.example.app.service.WalletServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final WalletServiceImpl walletService;
    /**
     * Поле сервис для работы с хранилищем операций по кошелькам
     */
    private final WalletOperationServiceImpl walletOperationService;
    /**
     * Поле маппер для операций по кошельку
     */
    private final WalletOperationMapper walletOperationMapper;
    /**
     * Поле маппер для кошельков
     */
    private final WalletMapper walletMapper;

    /**
     * Метод добавления операции по кошельку в хранилище операций сервиса через запрос
     *
     * @param walletOperationDto {@link WalletOperationDto}
     * @return сообщение об успешной операции и код 202,
     * в случае выброса исключения - сообщение ошибки и статус 409
     */
    @PostMapping("/wallet")
    public ResponseEntity<String> createWal(@Validated @RequestBody WalletOperationDto walletOperationDto) {
        WalletOperation walletOperation = walletOperationMapper.toWalletOperation(walletOperationDto);
        try {
            log.info("POST wallet: " + walletOperationDto.getAmount());
            walletOperationService.create(walletOperation);
            return new ResponseEntity<>(String.format("The operation " +
                            "for wallet %s for %d rubles was successfully completed",
                    walletOperationDto.getWalletId(), walletOperationDto.getAmount()),
                    HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            log.warn("Исключение в контроллере:", ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
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
        return walletMapper.toWalletDto(walletService.getById(walletId));
    }
}
