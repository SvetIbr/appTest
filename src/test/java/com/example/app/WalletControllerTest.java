package com.example.app;


import com.example.app.controller.WalletController;
import com.example.app.dto.WalletDto;
import com.example.app.dto.WalletOperationDto;
import com.example.app.error.ErrorHandler;
import com.example.app.error.exception.BadRequestException;
import com.example.app.error.exception.InsufficientFundsException;
import com.example.app.error.exception.NotFoundException;
import com.example.app.model.Wallet;
import com.example.app.service.WalletOperationService;
import com.example.app.service.WalletService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class WalletControllerTest {
    private MockMvc mvc;
    @InjectMocks
    private WalletController walletController;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    @Mock
    private WalletService walletService;
    @Mock
    private WalletOperationService walletOperationService;

    private final String walletId = "123e4567-e89b-12d3-a456-426655440000";
    private final Wallet wallet = Wallet.builder()
            .id(walletId)
            .balance(0L).build();
    private final WalletDto walletDto = WalletDto.builder()
            .id(walletId)
            .balance(0L).build();

    LocalDateTime now = LocalDateTime.now();
    private final WalletOperationDto walletOperationDtoWithoutId = WalletOperationDto.builder()
            .walletId(walletId)
            .operationType("DEPOSIT")
            .amount(100L)
            .created(now).build();

    private final WalletOperationDto walletOperationDtoWithId = WalletOperationDto.builder()
            .id(1L)
            .walletId("123e4567-e89b-12d3-a456-426655440000")
            .operationType("DEPOSIT")
            .amount(100L)
            .created(now).build();

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(walletController)
                .setControllerAdvice(ErrorHandler.class)
                .build();
    }

    @Test
    public void getWallet() throws Exception {
        when(walletService.getById(anyString())).thenReturn(wallet);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/wallets/{walletId}", walletId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(wallet.getId()), String.class))
                .andExpect(jsonPath("$.balance", is(wallet.getBalance()), Long.class));
        verify(walletService, times(1)).getById(anyString());
    }

    @Test
    public void getWalletWithNullId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/wallets/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getWalletWithWrongId() throws Exception {
        String walletId1 = "123e4567-e89b-12d3-a456-426655440020";
        when(walletService.getById(walletId1)).thenThrow(NotFoundException.class);
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/wallets/{walletId1}", walletId1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(walletService, times(1)).getById(anyString());
    }

//    @Test
//    public void createWalletOperation() throws Exception {
//        when(walletOperationService.create(any())).thenReturn(walletOperationDtoWithId);
//        mvc.perform(post("/api/v1/wallet")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(walletOperationDtoWithoutId)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(walletOperationDtoWithId.getId()), Long.class))
//                .andExpect(jsonPath("$.walletId",
//                        is(walletOperationDtoWithoutId.getWalletId()), String.class))
//                .andExpect(jsonPath("$.operationType",
//                        is(walletOperationDtoWithoutId.getOperationType()), String.class))
//                .andExpect(jsonPath("$.amount", is(100L), Long.class))
//                .andExpect(jsonPath("$.created", notNullValue()));
//
//        verify(walletOperationService, times(1)).create(any());
//    }

    @Test
    public void createWalletOperationWithNullWalletId() throws Exception {
        WalletOperationDto walletOperationDto1 = WalletOperationDto.builder()
                .operationType("DEPOSIT")
                .amount(100L)
                .created(now).build();

        mvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(walletOperationDto1)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createWalletOperationWithShortWalletId() throws Exception {
        WalletOperationDto walletOperationDto1 = WalletOperationDto.builder()
                .walletId("123e4567-e89b-12d3-a456-4266554400")
                .operationType("DEPOSIT")
                .amount(100L)
                .created(now).build();

        mvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(walletOperationDto1)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createWalletOperationWithLongWalletId() throws Exception {
        WalletOperationDto walletOperationDto1 = WalletOperationDto.builder()
                .walletId("123e4567-e89b-12d3-a456-42665544000098")
                .operationType("DEPOSIT")
                .amount(100L)
                .created(now).build();

        mvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(walletOperationDto1)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createWalletOperationWithNullOperationType() throws Exception {
        WalletOperationDto walletOperationDto1 = WalletOperationDto.builder()
                .walletId("123e4567-e89b-12d3-a456-426655440000")
                .amount(100L)
                .created(now).build();

        mvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(walletOperationDto1)))
                .andExpect(status().is4xxClientError());
    }

//    @Test
//    public void createWalletOperationWithInvalidOperationType() throws Exception {
//        when(walletOperationService.create(any())).thenThrow(BadRequestException.class);
//        WalletOperationDto walletOperationDto1 = WalletOperationDto.builder()
//                .walletId("123e4567-e89b-12d3-a456-426655440000")
//                .operationType("INVALID")
//                .amount(100L)
//                .created(now).build();
//
//        mvc.perform(post("/api/v1/wallet")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(walletOperationDto1)))
//                .andExpect(status().is4xxClientError());
//        verify(walletOperationService, times(1)).create(any());
//    }

    @Test
    public void createWalletOperationWithNullAmount() throws Exception {
        WalletOperationDto walletOperationDto1 = WalletOperationDto.builder()
                .walletId("123e4567-e89b-12d3-a456-426655440000")
                .operationType("DEPOSIT")
                .created(now).build();

        mvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(walletOperationDto1)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createWalletOperationWithNegativeAmount() throws Exception {
        WalletOperationDto walletOperationDto1 = WalletOperationDto.builder()
                .walletId("123e4567-e89b-12d3-a456-426655440000")
                .operationType("DEPOSIT")
                .amount(-1L)
                .created(now).build();

        mvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(walletOperationDto1)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createWalletOperationWithZeroAmount() throws Exception {
        WalletOperationDto walletOperationDto1 = WalletOperationDto.builder()
                .walletId("123e4567-e89b-12d3-a456-426655440000")
                .operationType("DEPOSIT")
                .amount(0L)
                .created(now).build();

        mvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(walletOperationDto1)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createWalletOperationWithNullData() throws Exception {
        mvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}

//    @Test
//    public void createWalletOperationWithNotFoundWalledId() throws Exception {
//        when(walletOperationService.create(any())).thenThrow(NotFoundException.class);
//        mvc.perform(post("/api/v1/wallet")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(walletOperationDtoWithoutId)))
//                .andExpect(status().isNotFound());
//
//        verify(walletOperationService, times(1)).create(any());
//    }

//    @Test
//    public void createWalletOperationWithdrawWithZeroBalance() throws Exception {
//        when(walletOperationService.create(any())).thenThrow(InsufficientFundsException.class);
//        mvc.perform(post("/api/v1/wallet")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(walletOperationDtoWithoutId)))
//                .andExpect(status().is4xxClientError());
//
//        verify(walletOperationService, times(1)).create(any());
//    }
//}
