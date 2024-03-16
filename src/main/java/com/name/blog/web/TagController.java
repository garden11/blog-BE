package com.name.blog.web;

import com.name.blog.provider.dto.PostTagDetailDTO;
import com.name.blog.provider.dto.TagDTO;
import com.name.blog.provider.service.PostTagService;
import com.name.blog.provider.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class TagController {

    private final TagService tagService;

    @GetMapping("/api/v1/tags")
    public List<TagDTO> getTags() {
        return tagService.getTagList();
    }
}
