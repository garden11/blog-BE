package com.name.blog.provider.service;

import com.name.blog.core.entity.Tag;
import com.name.blog.core.repository.TagRepository;
import com.name.blog.provider.dto.TagDTO;
import com.name.blog.provider.useCase.TagUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TagService implements TagUseCase {

    private final TagRepository tagRepository;

    @Override
    public List<TagDTO> getTagList() {

        List<TagDTO> tagDTOList = new ArrayList<>();

        List<Tag> tagList = tagRepository.findAll();

        for(Tag tag : tagList) {
            tagDTOList.add(TagDTO.of(tag));
        }

        return tagDTOList;
    }
}
