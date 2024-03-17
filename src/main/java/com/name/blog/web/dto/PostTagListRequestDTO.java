package com.name.blog.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostTagListRequestDTO {
    private Long postId;
    private List<String> tagList;
}
