package com.name.blog.core.entity;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

@Getter
@Entity
@Immutable
@NoArgsConstructor
public class PostTagInfo {
    @Id
    private Long id;
    private Long postId;
    private Long tagId;
    private String tagName;

    @QueryProjection
    public PostTagInfo(Long id, Long postId, Long tagId, String tagName) {
        this.id = id;
        this.postId = postId;
        this.tagId = tagId;
        this.tagName = tagName;
    }
}
