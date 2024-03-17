package com.name.blog.core.entity;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

@Getter
@Entity
@Immutable
@NoArgsConstructor
public class ProfileInfo {
    @Id
    private Long id;
    private String username;
    private Long profileImageId;
    private String profileImageUri;

    @QueryProjection
    public ProfileInfo(Long id, String username, Long profileImageId, String profileImageUri) {
        this.id = id;
        this.username = username;
        this.profileImageId = profileImageId;
        this.profileImageUri = profileImageUri;
    }
}
