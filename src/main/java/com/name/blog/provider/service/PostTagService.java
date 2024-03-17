package com.name.blog.provider.service;

import com.name.blog.core.entity.PostTag;
import com.name.blog.core.entity.PostTagInfo;
import com.name.blog.core.entity.Tag;
import com.name.blog.core.repository.PostTagInfoRepository;
import com.name.blog.core.repository.PostTagRepository;
import com.name.blog.core.repository.TagRepository;
import com.name.blog.provider.dto.PostTagDetailDTO;
import com.name.blog.provider.eventListener.event.PostTagsDeletedEvent;
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

        List<String> newTagNameList = postTagListRequestDTO.getTagList();


        for(String newTagName : newTagNameList) {
            List<String> tagNameList = new ArrayList<>();

            for(PostTagInfo postTagInfo : postTagInfoList) {
                tagNameList.add(postTagInfo.getTagName());
            }

            boolean isAdded = !tagNameList.contains(newTagName);

            if(isAdded) {
                Optional<Tag> optionalTag = tagRepository.findByName(newTagName);

                if (optionalTag.isPresent()) {
                    Tag tag = optionalTag.get();

                    postTagRepository.save(PostTag
                            .builder()
                            .postId(postId)
                            .tagId(tag.getId())
                            .build());
                } else {
                    Tag tag = tagRepository.save(Tag
                            .builder()
                            .name(newTagName)
                            .build());

                    postTagRepository.save(PostTag
                            .builder()
                            .postId(postId)
                            .tagId(tag.getId())
                            .build());
                }
            }
        };

        List<Long> deletedPostTagIdList = new ArrayList<>();
        List<Long> deletedPostTagTagIdList = new ArrayList<>();

        for(PostTagInfo postTagInfo : postTagInfoList) {
            boolean isDeleted = !newTagNameList.contains(postTagInfo.getTagName());

            if(isDeleted) {
                deletedPostTagIdList.add(postTagInfo.getId());
                deletedPostTagTagIdList.add(postTagInfo.getTagId());
            }
        };

        postTagRepository.deleteByIdIn(deletedPostTagIdList);
        eventPublisher.publishEvent(new PostTagsDeletedEvent(this, deletedPostTagTagIdList));
    }
}
