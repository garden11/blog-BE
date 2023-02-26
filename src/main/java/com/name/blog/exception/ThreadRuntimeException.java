package com.name.blog.exception;

public class ThreadRuntimeException  extends RuntimeException{
    public ThreadRuntimeException(){
        super(ErrorCode.THREAD_RUNTIME_FAILED.getMessage());
    }

    private ThreadRuntimeException(String msg){
        super(msg);
    }
}
