package com.name.blog.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.name.blog.core.security.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.name.blog.web.dto.UserRequestDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user")
@Where(clause="delete_yn='N'")
@DynamicInsert
@DynamicUpdate
public class User {
    @JsonIgnore
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password")
    @NotNull
    @Size(max = 100)
    private String password;

    @Column(name = "email", unique = true)
    @NotNull
    @Size(max = 40)
    private String email;
    
    @Column(name = "username")
    @NotNull
    @Size(min = 4, max = 20)
    private String username;

    @NotNull
    @JsonIgnore
    @Column(name = "role")
    @Size(max = 20)
    private String role;

    @Column(name = "delete_yn")
    @Size(min = 1, max = 1)
    private String deleteYN;

    @Column(name = "refresh_token")
    @Size(max = 100)
    private String refreshToken;

    @Column(name = "refresh_token_expires_at")
    private Long refreshTokenExpiresAt;
    
    @Builder
    public User(String email, String password, String username, Role role, String refreshToken, Long refreshTokenExpiresAt) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role.getCode();
        this.refreshToken = refreshToken;
        this.refreshTokenExpiresAt = refreshTokenExpiresAt;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken =  refreshToken;
    }

    public void updateUser(UserRequestDTO userRequestDTO) {
        this.email = userRequestDTO.getEmail();
    }

    public void updatePassword(String password){
        this.password = password;
    }

    public void deleteUser() {
        this.deleteYN = "Y";
    }
}