package com.name.blog.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@NoArgsConstructor
@Getter
@Entity
@Table(name="profile_view")
public class ProfileView {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "profile_image_id")
    private Long profileImageId;

    @Column(name = "profile_image_uri")
    private String profileImageUri;
}
