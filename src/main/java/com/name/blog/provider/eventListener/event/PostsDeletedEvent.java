package com.name.blog.provider.eventListener.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class PostsDeletedEvent extends ApplicationEvent {

    private List<Long> idList;

    public PostsDeletedEvent(Object source, List<Long> idList) {
        super(source);
        this.idList = idList;
    }
}
