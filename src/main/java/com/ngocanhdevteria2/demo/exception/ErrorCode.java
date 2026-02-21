package com.ngocanhdevteria2.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_INVALID(1003, "Username must be at least {min} chars", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} chars", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1001, "INVALID_KEY", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTS(1005, "User Not exists", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "UNAUTHENTICATED", HttpStatus.UNAUTHORIZED),
    USER_EXISTED(1002,"User existsted", HttpStatus.BAD_REQUEST),
    INVALID_BIRTHDATE(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;


}
