package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.TagDTO;

import java.util.List;

public interface TagUseCase {
    List<TagDTO> getTagList();
}
