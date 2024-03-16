package com.name.blog.provider.eventListener;

import com.name.blog.constants.Retentions;
import com.name.blog.core.repository.ProfileImageRepository;
import com.name.blog.provider.eventListener.event.ProfileDeletedEvent;
import com.name.blog.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Component
public class ProfileEventListener {

    private final ProfileImageRepository profileImageRepository;

    private final DateUtil dateUtil = new DateUtil();

    @EventListener
    @Transactional
    public void handleProfileTagDeletedEvent(ProfileDeletedEvent event) {

        Long id = event.getId();
        Long expiresAt = dateUtil.createEpochSecondPlus(Retentions.PROFILE_IMAGE_DAYS.getValue(), ChronoUnit.DAYS);

        profileImageRepository.updateNotUsingByProfileId(id, expiresAt);
    }
}
