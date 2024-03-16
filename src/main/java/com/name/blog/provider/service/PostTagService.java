package com.name.blog.provider.service;

import com.name.blog.core.entity.PostTag;
import com.name.blog.core.entity.PostTagInfo;
import com.name.blog.core.entity.Tag;
import com.name.blog.core.repository.PostTagInfoRepository;
import com.name.blog.core.repository.PostTagRepository;
import com.name.blog.core.repository.TagRepository;
import com.name.blog.provider.dto.PostTagDetailDTO;
import com.name.blog.provider.eventListener.event.PostTagDeletedEvent;
import com.name.blog.provider.useCase.PostTagUseCase;
import com.name.blog.web.dto.PostTagListRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostTagService implements PostTagUseCase {

    private final ApplicationEventPublisher eventPublisher;

    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;
    private final PostTagInfoRepository postTagInfoRepository;

    @Override
    @Transactional
    public List<PostTagDetailDTO> getPostTagDetailListByPostId(Long postId) {
        List<PostTagDetailDTO> postTagDetailDTOList = new ArrayList<>();

        List<PostTagInfo> postTagInfoList = postTagInfoRepository.findByPostId(postId);

        for(PostTagInfo postTagInfo : postTagInfoList) {

            postTagDetailDTOList.add(PostTagDetailDTO.of(postTagInfo));

        }

        return postTagDetailDTOList;
    }

    @Override
    @Transactional
    public void savePostTagList(PostTagListRequestDTO postTagListRequestDTO) {
        Long postId = postTagListRequestDTO.getPostId();
        List<PostTagInfo> postTagInfoList = postTagInfoRepository.findByPostId(postId);

        List<String> newTagList = postTagListRequestDTO.getTagList();

        for(String newTag : newTagList) {
            List<String> tagList = postTagInfoList.stream()
                    .map(postTagInfo -> {
                        return postTagInfo.getTagName();
                    })
                    .collect(Collectors.toList());

            boolean isAdded = !tagList.contains(newTag);

            if(isAdded) {
                Optional<Tag> optionalTag = tagRepository.findByName(newTag);

                if(optionalTag.isPresent()) {
                    Tag tag = optionalTag.get();

                    postTagRepository.save(PostTag
                            .builder()
                            .postId(postId)
                            .tagId(tag.getId())
                            .build());
                } else {
                    Tag tag = tagRepository.save(Tag
                            .builder()
                                    .name(newTag)
                            .build());

                    postTagRepository.save(PostTag
                            .builder()
                            .postId(postId)
                            .tagId(tag.getId())
                            .build());
                }
            }
        };

        for(PostTagInfo postTagInfo : postTagInfoList) {
            boolean isDeleted = !newTagList.contains(postTagInfo.getTagName());

            if(isDeleted) {
                Long tagId = postTagInfo.getTagId();

                postTagRepository.deleteByPostIdAndTagId(postId, tagId);
                eventPublisher.publishEvent(new PostTagDeletedEvent(this, tagId));
            }
        };
    }
}
