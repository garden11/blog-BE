package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.CommentDTO;
import com.name.blog.web.dto.CommentRequestDTO;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

public interface CommentUseCase {
    @Transactional
    CommentDTO insertComment(@RequestBody CommentRequestDTO commentRequestDTO);

    @Transactional
    CommentDTO deleteCommentById(Long id);
}
