package com.example.app.error;

import com.example.app.error.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Класс обработчика возникающих исключений
 *
 * @author Светлана Ибраева
 * @version 1.0
 */
@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    /**
     * Метод обработки исключений при отсутствии искомых объектов
     *
     * @return сообщение с описанием причины возникновения ошибки и статусом 404
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final NotFoundException e) {
        return new ErrorResponse(e.getMessage(), LocalDateTime.now());
    }

    /**
     * Метод обработки исключений при отсутствии невалидных данных при запросах
     *
     * @return сообщение с описанием причины возникновения ошибки и статусом 400
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(final BadRequestException e) {
        return new ErrorResponse(e.getMessage(), LocalDateTime.now());
    }

    /**
     * Метод обработки исключений при невозможной операции (баланс меньше суммы на списание)
     *
     * @return сообщение с описанием причины возникновения ошибки и статусом 403
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleInsufficientFundsException(final InsufficientFundsException e) {
        return new ErrorResponse(e.getMessage(), LocalDateTime.now());
    }

}
