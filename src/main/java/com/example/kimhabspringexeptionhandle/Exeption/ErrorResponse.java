package com.example.kimhabspringexeptionhandle.Exeption;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(int value, String an_error_occurred, String message, String description) {
        this.error = an_error_occurred;
        this.status = value;
        this.message = message;
        this.path = description;
    }
}
