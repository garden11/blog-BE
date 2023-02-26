package com.name.blog.exception;

public class ReissueAuthTokensFailedException extends RuntimeException{
    public ReissueAuthTokensFailedException(){
        super(ErrorCode.REISSUE_AUTH_TOKENS_FAILED.getMessage());
    }

    private ReissueAuthTokensFailedException(String msg){
        super(msg);
    }
}
