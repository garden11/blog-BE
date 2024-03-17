package com.name.blog.provider.eventListener;

import com.name.blog.constants.Retentions;
import com.name.blog.core.entity.Post;
import com.name.blog.core.entity.Profile;
import com.name.blog.core.repository.CommentRepository;
import com.name.blog.core.repository.PostRepository;
import com.name.blog.core.repository.ProfileRepository;
import com.name.blog.provider.eventListener.event.PostDeletedEvent;
import com.name.blog.provider.eventListener.event.PostsDeletedEvent;
import com.name.blog.provider.eventListener.event.ProfileDeletedEvent;
import com.name.blog.provider.eventListener.event.WithdrawalEvent;
import com.name.blog.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class WithdrawalEventListener {

    private final ApplicationEventPublisher eventPublisher;

    private final ProfileRepository profileRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    private final DateUtil dateUtil = new DateUtil();

    @EventListener
    @Transactional
    public void handleWithdrawalEvent(WithdrawalEvent event) {

        String username = event.getUsername();

        Long postExpiresAt = dateUtil.createEpochSecondPlus(Retentions.POST_DAYS.getValue(), ChronoUnit.DAYS);
        Long commentExpiresAt = dateUtil.createEpochSecondPlus(Retentions.COMMENT_DAYS.getValue(), ChronoUnit.DAYS);

        Optional<Profile> optionalProfile = profileRepository.findByUsername(username);
        List<Post> postList = postRepository.findByUsername(username);

        Long profileId = optionalProfile.orElseThrow().getId();
        List<Long> postIdList = new ArrayList<>();

        for(Post post : postList) {
            postIdList.add(post.getId());
        }

        profileRepository.updateDeletingById(profileId);
        postRepository.updateDeletingByIdIn(postIdList, postExpiresAt);
        commentRepository.updateDeletingByUsername(username, commentExpiresAt);

        eventPublisher.publishEvent(new ProfileDeletedEvent(this, profileId));
        eventPublisher.publishEvent(new PostsDeletedEvent(this, postIdList));
    }
}
