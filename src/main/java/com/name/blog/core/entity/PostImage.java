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
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="post_image")
@DynamicInsert
@DynamicUpdate
public class PostImage {
	@JsonIgnore
    @Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "post_id")
	private Long postId;

	@NotNull
	@Column(name = "uri")
	@Size(max = 400)
	private String uri;

	@NotNull
	@Column(name = "original_name")
	@Size(max = 260)
	private String originalName;

	@NotNull
	@Column(name = "name")
	@Size(max = 260)
	private String name;

	@Column(name = "use_yn")
	@Size(max = 1)
	private String useYN;

	@Column(name = "expires_at")
	private Long expiresAt;
	
    @Builder
    public PostImage(Long postId, String uri, String originalName, String name) {
    	this.postId = postId;
        this.uri = uri;
        this.originalName = originalName;
        this.name = name;
    }
}
