package com.name.blog.exception;

public class CustomAccessTokenRuntimeException extends RuntimeException {
    public CustomAccessTokenRuntimeException(){
        super(ErrorCode.INVALID_ACCESS_TOKEN.getMessage());
    }

    public CustomAccessTokenRuntimeException(Exception ex){
        super(ex);
    }
}
