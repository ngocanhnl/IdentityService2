package com.ngocanhdevteria2.demo.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception"),
    USERNAME_INVALID(1003, "USERNAME_INVALID"),
    INVALID_PASSWORD(1004, "INVALID_PASSWORD"),
    INVALID_KEY(1001, "INVALID_KEY"),
    USER_NOT_EXISTS(1005, "User Not exists"),
    UNAUTHORIZED(1006, "Unauthorized"),
    USER_EXISTED(1002,"User existsted");

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
