package com.name.blog.exception;

public class WithdrawalFailedException extends RuntimeException {
    public WithdrawalFailedException(){
        super(ErrorCode.WITHDRAWAL_FAILED.getMessage());
    }

    private WithdrawalFailedException(String msg){
        super(msg);
    }
}
