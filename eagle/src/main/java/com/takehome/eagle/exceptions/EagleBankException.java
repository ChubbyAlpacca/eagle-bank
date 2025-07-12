package com.takehome.eagle.exceptions;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatusCode;

@Data
@ToString(callSuper = true)
public class EagleBankException extends RuntimeException {

    private final HttpStatusCode httpstatusCode;

    public EagleBankException(String message, HttpStatusCode code) {
        super(message);
        httpstatusCode = code;
    }
}
