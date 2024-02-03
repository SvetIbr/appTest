package com.example.app.mapper;

import com.example.app.dto.WalletOperationDto;
import com.example.app.model.WalletOperation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.example.app.util.Constants.DATE_TIME_FORMAT;

@Mapper(componentModel = "spring")
public interface WalletOperationMapper {
    //@Mapping(source = "created", target = "created", dateFormat = DATE_TIME_FORMAT)
    WalletOperation toWalletOperation(WalletOperationDto walletOperationDto);

    //@Mapping(source = "created", target = "created", dateFormat = DATE_TIME_FORMAT)
    WalletOperationDto toWalletOperationDto(WalletOperation save);
}
