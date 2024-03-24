package com.example.kimhabspringexeptionhandle;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(int value, String an_error_occurred, String message, String description) {
    }
}
