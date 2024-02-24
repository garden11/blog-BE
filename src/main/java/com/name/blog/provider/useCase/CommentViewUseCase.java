package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.CommentViewDTO;
import org.springframework.data.domain.Page;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CommentViewUseCase {
    @Transactional
    Page<CommentViewDTO> selectCommentViewListByPostId(Long postId, Integer page);

//    @Transactional
//    Map<String, Object> selectCommentViewCountByPostId(Long postId, Integer page);
}
