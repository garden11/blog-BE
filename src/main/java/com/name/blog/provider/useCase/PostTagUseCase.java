package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.PostTagDetailDTO;
import com.name.blog.web.dto.PostTagListRequestDTO;

import java.util.List;

public interface PostTagUseCase {
    List<PostTagDetailDTO> getPostTagDetailListByPostId(Long postId);

    void savePostTagList(PostTagListRequestDTO postTagListRequestDTO);
}
