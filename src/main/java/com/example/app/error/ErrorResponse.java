package com.example.app.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.example.app.util.Constants.DATE_TIME_FORMAT;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
    private final LocalDateTime timestamp;
}
