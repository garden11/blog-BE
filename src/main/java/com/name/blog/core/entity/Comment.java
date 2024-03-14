package com.name.blog.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@NoArgsConstructor
@Getter
@Entity
@Where(clause="delete_yn='N'")
@Table(name = "comment")
@DynamicInsert
@DynamicUpdate
public class Comment {
	@JsonIgnore
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotNull
    @Column(name = "post_id")
	private Long postId;

    @NotNull
    @Column(name = "username")
    @Size(min = 4, max = 20)
	private String username;

    @NotNull
    @Column(name = "content")
    @Size(min = 1, max = 200)
	private String content;

    @NotNull
    @Column(name = "registered_at")
    private Long registeredAt;

    @Column(name = "delete_yn")
    @Size(max = 1)
    private String deleteYN;

    @Column(name = "expires_at")
    private Long expiresAt;


    @Builder
    public Comment(String username, Long postId, String content, Long registeredAt) {
        this.postId = postId;
        this.username = username;
        this.content = content;
    	this.registeredAt = registeredAt;
    }
}
