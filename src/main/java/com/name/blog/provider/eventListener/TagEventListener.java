package com.name.blog.provider.eventListener;

import com.name.blog.core.repository.PostTagRepository;
import com.name.blog.core.repository.TagRepository;
import com.name.blog.provider.eventListener.event.PostTagDeletedEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TagEventListener {

    private final PostTagRepository postTagRepository;
    private final TagRepository tagRepository;

    @EventListener
    @Transactional
    public void handlePostTagDeletedEvent(PostTagDeletedEvent event) {
        Long tagId = event.getTagId();

        if(!postTagRepository.existsByTagId(tagId)) {
            tagRepository.deleteById(tagId);
        };
    }
}
