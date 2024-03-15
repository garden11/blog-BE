package com.name.blog.constants;

import lombok.Getter;

@Getter
public enum Retention {
    ACCESS_TOKEN_MINUTES(20L),
    REFRESH_TOKEN_DAYS(1L);

    private final Long value;

    Retention(Long value) {
        this.value = value;
    }

}
