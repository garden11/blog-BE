package com.name.blog.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.name.blog.web.dto.PostRequestDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="post")
@DynamicInsert
@DynamicUpdate
public class Post {
	@JsonIgnore
    @Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "category_id")
	private Long categoryId;

    @NotNull
    @Column(name = "username")
    @Size(min = 4, max = 20)
    private String username;

    @Column(name = "title")
    @Size(max = 200)
    private String title;

    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "registered_at")
    private Long registeredAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    @Column(name = "delete_yn")
    @Size(max = 1)
    private String deleteYN;

    @Column(name = "register_yn")
    @Size(max = 1)
    private String registerYN;

    @Column(name = "expires_at")
    private Long expiresAt;
    
    @Builder
    public Post(String username, String content, Long createdAt) {
        this.username = username;
        this.createdAt = createdAt;
        this.content = content;
    }

    public void updatePost(PostRequestDTO postRequestDTO) {
        this.categoryId = Long.valueOf(postRequestDTO.getCategoryId());
        this.title = postRequestDTO.getTitle();
        this.content = postRequestDTO.getContent();

        if (postRequestDTO.getRegisteredAt() != null) { this.registeredAt= Long.valueOf(postRequestDTO.getRegisteredAt());}
        if (postRequestDTO.getUpdatedAt() != null) { this.updatedAt = Long.valueOf(postRequestDTO.getUpdatedAt());}
    }

    public void registerPost() {
        this.registerYN = "Y";
    }
}
