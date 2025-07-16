package com.takehome.eagle.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.HttpStatusCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class EagleBankException extends RuntimeException {

    private final HttpStatusCode httpstatusCode;
    private final Object errorResponse; // optional error body

    public EagleBankException(String message, HttpStatusCode code) {
        super(message);
        this.httpstatusCode = code;
        this.errorResponse = null;
    }

    public EagleBankException(String message, HttpStatusCode code, Object errorResponse) {
        super(message);
        this.httpstatusCode = code;
        this.errorResponse = errorResponse;
    }
}
