package com.name.blog.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Entity
@Table(name="profile")
@DynamicInsert
@DynamicUpdate
public class Profile {
    @JsonIgnore
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "username")
    @Size(min = 4, max = 20)
    private String username;

    @Column(name = "delete_yn")
    @Size(max = 1)
    private String deleteYN;

    @Builder
    public Profile(String username) {
        this.username = username;
    }
}
