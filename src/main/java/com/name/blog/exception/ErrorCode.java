package com.name.blog.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    AUTHENTICATION_FAILED(401, "AUTH001", " AUTHENTICATION_FAILED."),
    SIGNIN_FAILED(401, "AUTH002", " SIGNIN_FAILED."),
    SIGNUP_FAILED(401, "AUTH003", " SIGNUP_FAILED."),
    INVALID_ACCESS_TOKEN(401, "AUTH004", "INVALID_ACCESS_TOKEN."),
    USER_UPDATE_FAILED(401, "AUTH007", "USER_UPDATE_FAILED."),
    RESET_PASSWORD_FAILED(401, "AUTH008", "RESET_PASSWORD_FAILED."),
    WITHDRAWAL_FAILED(401, "AUTH006", "WITHDRAWAL_FAILED."),
    REISSUE_AUTH_TOKENS_FAILED(401, "AUTH007", "REISSUE_AUTH_TOKENS_FAILED."),
    MAIL_SEND_FAILED(500,"INTERNAL_SERVER001", "MAIL_SEND_FAILED."),
    THREAD_RUNTIME_FAILED(500,"INTERNAL_SERVER002", "THREAD_RUNTIME_FAILED.");

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}