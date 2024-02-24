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
@Table(name="mail_process")
@DynamicInsert
@DynamicUpdate
public class MailProcess {
    @JsonIgnore
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "email")
    @Size(max = 40)
    private String email;

    @NotNull
    @Column(name = "process_token")
    @Size(max = 100)
    private String processToken;

    @Column(name = "process_yn")
    @Size(max = 1)
    private String processYN;

    @Column(name = "expires_at")
    private Long expiresAt;

    @Builder
    public MailProcess(String email, String processToken) {
        this.email = email;
        this.processToken = processToken;
    }
}
