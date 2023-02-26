package com.name.blog.exception;

public class UserUpdateFailedException extends RuntimeException {
    public UserUpdateFailedException(){
        super(ErrorCode.USER_UPDATE_FAILED.getMessage());
    }

    private UserUpdateFailedException(String msg){
        super(msg);
    }
}
