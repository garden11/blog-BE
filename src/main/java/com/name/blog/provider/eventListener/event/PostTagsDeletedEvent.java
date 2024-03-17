package com.name.blog.provider.eventListener.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class PostTagsDeletedEvent extends ApplicationEvent  {

    private List<Long> tagIdList;

    public PostTagsDeletedEvent(Object source, List<Long> tagIdList) {
        super(source);
        this.tagIdList = tagIdList;
    }
}
