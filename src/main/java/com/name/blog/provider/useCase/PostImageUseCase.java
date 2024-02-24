package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.PostImageDTO;
import com.name.blog.web.dto.PostImageRequestDTO;

import jakarta.transaction.Transactional;
import java.util.List;

public interface PostImageUseCase {
    @Transactional
    PostImageDTO insertPostImage(PostImageRequestDTO postImageRequestDTO);
}
