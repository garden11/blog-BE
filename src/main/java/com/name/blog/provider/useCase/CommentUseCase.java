package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.CommentDTO;
import com.name.blog.provider.dto.CommentDetailDTO;
import com.name.blog.web.dto.CommentRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;

public interface CommentUseCase {
    Page<CommentDetailDTO> selectCommentDetailListByPostId(Long postId, Integer page);

    CommentDTO insertComment(@RequestBody CommentRequestDTO commentRequestDTO);

    void deleteCommentById(Long id);
}
