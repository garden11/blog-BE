package com.name.blog.provider.eventListener.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class WithdrawalEvent extends ApplicationEvent {

    private String username;

    public WithdrawalEvent(Object source, String username) {
        super(source);
        this.username = username;
    }
}
