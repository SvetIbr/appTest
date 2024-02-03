package com.example.app.mapper;

import com.example.app.dto.WalletDto;
import com.example.app.model.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletDto toWalletDto(Wallet wallet);
}
