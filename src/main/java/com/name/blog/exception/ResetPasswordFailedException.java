package com.name.blog.exception;

public class ResetPasswordFailedException extends RuntimeException {
    public ResetPasswordFailedException(){
        super(ErrorCode.RESET_PASSWORD_FAILED.getMessage());
    }

    private ResetPasswordFailedException(String msg){
        super(msg);
    }
}
