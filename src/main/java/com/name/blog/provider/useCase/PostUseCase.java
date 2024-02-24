package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.PostDTO;
import com.name.blog.web.dto.PostRequestDTO;

import jakarta.transaction.Transactional;
import java.util.Optional;

public interface PostUseCase {
    @Transactional
    Optional<PostDTO> selectPostByPostId(Long id);

    @Transactional
    PostDTO insertPost(PostRequestDTO postRequestDTO);

    @Transactional
    PostDTO updatePostById(Long id, PostRequestDTO postRequestDTO);

    @Transactional
    void deletePostById(Long id);

    @Transactional
    boolean isValidPost(String username, Long id);
}
