package com.name.blog.provider.eventListener;

import com.name.blog.constants.Retentions;
import com.name.blog.core.entity.PostTag;
import com.name.blog.core.repository.PostImageRepository;
import com.name.blog.core.repository.PostTagRepository;
import com.name.blog.provider.eventListener.event.PostDeletedEvent;
import com.name.blog.provider.eventListener.event.PostTagsDeletedEvent;
import com.name.blog.provider.eventListener.event.PostsDeletedEvent;
import com.name.blog.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PostEventListener {

    private final ApplicationEventPublisher eventPublisher;

    private final PostImageRepository postImageRepository;
    private final PostTagRepository postTagRepository;

    private final DateUtil dateUtil = new DateUtil();

    @EventListener
    @Transactional
    public void handlePostDeletedEvent(PostDeletedEvent event) {

        Long id = event.getId();

        Long postImageExpiresAt = dateUtil.createEpochSecondPlus(Retentions.POST_IMAGE_DAYS.getValue(), ChronoUnit.DAYS);

        List<PostTag> postTagList = postTagRepository.findByPostId(id);

        List<Long> postTagTagIdList = new ArrayList<>();

        for(PostTag postTag : postTagList) {
            postTagTagIdList.add(postTag.getTagId());
        }

        postImageRepository.updateNotUsingByPostId(id, postImageExpiresAt);
        postTagRepository.deleteByPostId(id);
        eventPublisher.publishEvent(new PostTagsDeletedEvent(this, postTagTagIdList));
    }

    @EventListener
    @Transactional
    public void handlePostsDeletedEvent(PostsDeletedEvent event) {

        List<Long> idList = event.getIdList();

        Long postImageExpiresAt = dateUtil.createEpochSecondPlus(Retentions.POST_IMAGE_DAYS.getValue(), ChronoUnit.DAYS);

        List<Long> postTagTagIdList = new ArrayList<>();

        List<PostTag> postTagList = postTagRepository.findByPostIdIn(idList);

        for(PostTag postTag : postTagList) {
            postTagTagIdList.add(postTag.getTagId());
        }

        List<Long> distinctPostTagTagIdList = postTagTagIdList.stream()
                .distinct()
                .collect(Collectors.toList());

        postImageRepository.updateNotUsingByPostIdIn(idList, postImageExpiresAt);
        postTagRepository.deleteByPostIdIn(idList);
        eventPublisher.publishEvent(new PostTagsDeletedEvent(this, distinctPostTagTagIdList));
    }
}
