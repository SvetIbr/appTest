package com.example.app.mapper;

import com.example.app.dto.WalletOperationDto;
import com.example.app.model.WalletOperation;
import org.mapstruct.Mapper;

/**
 * Mapper-класс для преобразования объектов сервиса операций по кошелькам
 *
 * @author Светлана Ибраева
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface WalletOperationMapper {
    /**
     * Метод преобразования объекта WalletOperationDto в WalletOperation
     *
     * @param walletOperationDto {@link WalletOperationDto}
     * @return {@link WalletOperation}
     */
    WalletOperation toWalletOperation(WalletOperationDto walletOperationDto);

    /**
     * Метод преобразования объекта WalletOperation в WalletOperationDto
     *
     * @param save {@link WalletOperation}
     * @return {@link WalletOperationDto}
     */
    WalletOperationDto toWalletOperationDto(WalletOperation save);
}
