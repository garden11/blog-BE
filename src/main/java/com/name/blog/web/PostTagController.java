package com.name.blog.web;

import com.name.blog.core.security.Auth;
import com.name.blog.core.security.Role;
import com.name.blog.provider.dto.PostDTO;
import com.name.blog.provider.dto.PostDetailDTO;
import com.name.blog.provider.dto.PostTagDetailDTO;
import com.name.blog.provider.service.PostTagService;
import com.name.blog.web.dto.PostRequestDTO;
import com.name.blog.web.dto.PostTagListRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PostTagController {

    private final PostTagService postTagService;

    @GetMapping("/api/v1/post/{post-id}/tags")
    public List<PostTagDetailDTO> getPostTagDetailsByPostId(@PathVariable("post-id") Long postId) {
        return postTagService.getPostTagDetailListByPostId(postId);
    }

    @PostMapping("/api/v1/tags")
    @Auth(roles = {Role.USER})
    public void savePostTags(@RequestBody PostTagListRequestDTO postTagListRequestDTO) {
        postTagService.savePostTagList(postTagListRequestDTO);
    }
}
