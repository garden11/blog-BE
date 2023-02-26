package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.PostViewDTO;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PostViewUseCase {
    @Transactional
    Optional<PostViewDTO> selectPostViewById(Long id);

    @Transactional
    Page<PostViewDTO> selectPostViewListByUsername(String username, Integer page);

//    @Transactional
//    Map<String, Object> selectPostViewCountByUsername(String username, Integer page);

    @Transactional
    Page<PostViewDTO> selectPostViewListByCategoryId(Long categoryId, Integer page);

//    @Transactional
//    Map<String, Object> selectPostViewCountByCategoryId(Long categoryId, Integer page);
}
