package com.example.app.mapper;

import com.example.app.dto.WalletDto;
import com.example.app.model.Wallet;
import org.mapstruct.Mapper;

/**
 * Mapper-класс для преобразования объектов сервиса кошельков
 *
 * @author Светлана Ибраева
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface WalletMapper {
    /**
     * Метод преобразования объекта Wallet в WalletDto
     *
     * @param wallet {@link Wallet}
     * @return {@link WalletDto}
     */
    WalletDto toWalletDto(Wallet wallet);
}
