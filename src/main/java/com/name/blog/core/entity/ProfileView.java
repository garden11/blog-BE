package com.name.blog.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
