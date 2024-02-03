package com.example.app.controller;

import com.example.app.dto.WalletDto;
import com.example.app.dto.WalletOperationDto;
import com.example.app.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(path = "api/v1")
public class WalletController {
    private final WalletService walletService;
    @PostMapping("/wallet")
    @ResponseStatus(HttpStatus.OK)
    public WalletOperationDto createWal(@Validated @RequestBody WalletOperationDto walletOperationDto) {
        //log.info("POST: создание операции с параметрами: " + walletOperationDto);
        return walletService.create(walletOperationDto);
    }

    @GetMapping("/wallets/{WALLET_UUID}")
    public WalletDto getWallets(@PathVariable("WALLET_UUID") String walletId) {
        //log.info(String.format("GET: получение данных с параметрами: %s", walletId));
        return walletService.getById(walletId);
    }
}
