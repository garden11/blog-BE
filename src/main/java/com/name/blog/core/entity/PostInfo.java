package com.name.blog.core.entity;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.annotation.Immutable;

@Getter
@Entity
@Immutable
public class PostInfo {
    @Id
    private Long id;
    private String username;
    private String title;
    private String content;
    private Long registeredAt;
    private Long updatedAt;
    private Long thumbnailImageId;
    private String thumbnailImageUri;

    @QueryProjection
    public PostInfo(Long id, String username, String title, String content, Long registeredAt, Long updatedAt, Long thumbnailImageId, String thumbnailImageUri) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.registeredAt = registeredAt;
        this.updatedAt = updatedAt;
        this.thumbnailImageId = thumbnailImageId;
        this.thumbnailImageUri = thumbnailImageUri;
    }
}
