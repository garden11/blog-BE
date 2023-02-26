package com.name.blog.exception;

public class SignUpFailedException extends RuntimeException {
    public SignUpFailedException(){
        super(ErrorCode.SIGNUP_FAILED.getMessage());
    }

    private SignUpFailedException(String msg){
        super(msg);
    }
}
