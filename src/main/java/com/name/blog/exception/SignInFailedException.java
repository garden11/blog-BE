package com.name.blog.exception;

public class SignInFailedException extends RuntimeException {
    public SignInFailedException(){
        super(ErrorCode.SIGNIN_FAILED.getMessage());
    }

    private SignInFailedException(String msg){
        super(msg);
    }
}
