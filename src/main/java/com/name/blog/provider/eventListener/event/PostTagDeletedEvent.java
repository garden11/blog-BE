package com.name.blog.provider.eventListener.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PostTagDeletedEvent extends ApplicationEvent {

    private Long tagId;

    public PostTagDeletedEvent(Object source, Long tagId) {
        super(source);
        this.tagId = tagId;
    }
}
