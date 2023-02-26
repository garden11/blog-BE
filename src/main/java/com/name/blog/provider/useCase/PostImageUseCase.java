package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.PostImageDTO;
import com.name.blog.web.dto.PostImageRequestDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface PostImageUseCase {
    @Transactional
    PostImageDTO insertPostImage(PostImageRequestDTO postImageRequestDTO);

    @Transactional
    void updatePostImagesByPostIdAndUriList(Long postId, List<String> uriList);
}
