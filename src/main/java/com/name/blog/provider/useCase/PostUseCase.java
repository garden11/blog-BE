package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.PostDTO;
import com.name.blog.provider.dto.PostDetailDTO;
import com.name.blog.web.dto.PostRequestDTO;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PostUseCase {
    Page<PostDetailDTO> selectPostDetailListByUsername(String username, Integer page);
    Page<PostDetailDTO> selectPostDetailListByCategoryId(Long categoryId, Integer page);
    PostDetailDTO selectPostDetailById(Long id);
    Optional<PostDTO> selectPostByPostId(Long id);
    PostDTO insertPost(PostRequestDTO postRequestDTO);
    PostDTO updatePostById(Long id, PostRequestDTO postRequestDTO);
    void deletePostById(Long id);
    boolean isValidPost(String username, Long id);
}
