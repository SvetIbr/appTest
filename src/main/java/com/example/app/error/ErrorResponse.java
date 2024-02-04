package com.example.app.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.example.app.util.Constants.DATE_TIME_FORMAT;

/**
 * Класс ответов контроллеров при возникновении исключений с полями <b>message</b> и <b>timestamp</b>
 *
 * @author Светлана Ибраева
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {
    /**
     * Поле сообщение
     */
    private final String message;

    /**
     * Поле временная отметка возникновения
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
    private final LocalDateTime timestamp;
}
