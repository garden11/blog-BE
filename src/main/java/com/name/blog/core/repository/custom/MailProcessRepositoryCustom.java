package com.name.blog.core.repository.custom;

public interface MailProcessRepositoryCustom {
    Long updateProcessingById(Long id);
    Long updateProcessingByEmail(String email);
}
