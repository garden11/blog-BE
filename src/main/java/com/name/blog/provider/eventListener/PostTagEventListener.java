package com.name.blog.provider.eventListener;

import com.name.blog.core.repository.PostTagRepository;
import com.name.blog.core.repository.TagRepository;
import com.name.blog.provider.eventListener.event.PostTagsDeletedEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PostTagEventListener {

    private final PostTagRepository postTagRepository;
    private final TagRepository tagRepository;

    @EventListener
    @Transactional
    public void handlePostTagsDeletedEvent(PostTagsDeletedEvent event) {
        List<Long> tagIdList = event.getTagIdList();

        for(Long tagId : tagIdList) {
            if(!postTagRepository.existsByTagId(tagId)) {
                tagRepository.deleteById(tagId);
            };
        }
    }
}
