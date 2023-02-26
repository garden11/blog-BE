package com.name.blog.exception;

public class MailSendFailedException extends RuntimeException{
    public MailSendFailedException(){
        super(ErrorCode.MAIL_SEND_FAILED.getMessage());
    }

    private MailSendFailedException(String msg){
        super(msg);
    }
}
