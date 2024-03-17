package com.name.blog.provider.eventListener.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PostDeletedEvent extends ApplicationEvent {

    private Long id;

    public PostDeletedEvent(Object source, Long id) {
        super(source);
        this.id = id;
    }
}
