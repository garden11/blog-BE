package com.name.blog.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="post_view")
public class PostView {
	@Id
    @Column(name = "id")
	private Long id;
	
    @Column(name = "username")
	private String username;
    
    @Column(name = "category_id")
	private Long categoryId;
    
    @Column(name = "category_name")
	private String categoryName;
    
    @Column(name = "title")
	private String title;
    
    @Column(name = "content")
	private String content;
    
    @Column(name = "registered_at")
	private Long registeredAt;
    
    @Column(name = "updated_at")
	private Long updatedAt;
    
    @Column(name = "thumbnail_image_id")
	private Long thumbnailImageId;
    
    @Column(name = "thumbnail_image_uri")
	private String thumbnailImageUri;
}
