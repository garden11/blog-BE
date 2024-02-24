package com.name.blog.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="comment_view")
public class CommentView {
	@Id
    @Column(name = "id")
	private Long id;

    @Column(name = "post_id")
	private Long postId;

    @Column(name = "username")
	private String username;

    @Column(name = "content")
	private String content;

    @Column(name = "registered_at")
	private Long registeredAt;

	@Column(name = "profile_id")
	private Long profileId;

    @Column(name = "profile_image_id")
	private Long profileImageId;

    @Column(name = "profile_image_uri")
	private String profileImageUri;
}
