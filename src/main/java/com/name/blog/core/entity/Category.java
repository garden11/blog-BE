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
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "category")
@Where(clause = "delete_yn='N'")
@DynamicInsert
@DynamicUpdate
public class Category {
	@JsonIgnore
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotNull
    @Column(name = "username")
    @Size(min = 4, max = 20)
	private String username;

    @NotNull
    @Column(name = "name")
    @Size(max = 10)
	private String name;

    @Column(name = "delete_yn")
    @Size(max = 1)
    private String deleteYN;

    @Column(name = "expires_at")
    private Long expiresAt;
	
    @Builder
    public Category(String username, String name) {
        this.username = username;
        this.name = name;
    }
    
    public void updateCategory(String name) {
    	this.name = name;
    }
}
