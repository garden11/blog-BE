package com.name.blog.core.repository.custom.impl;

import com.name.blog.core.repository.custom.MailProcessRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.name.blog.core.entity.QMailProcess.mailProcess;

@RequiredArgsConstructor
@Repository
public class MailProcessRepositoryImpl implements MailProcessRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Long updateProcessingById(Long id) {
        return queryFactory
                .update(mailProcess)
                .set(mailProcess.processYN, "Y")
                .where(mailProcess.id.eq(id))
                .execute();
    }

    @Override
    public Long updateProcessingByEmail(String email) {
        return queryFactory
                .update(mailProcess)
                .set(mailProcess.processYN, "Y")
                .where(mailProcess.email.eq(email))
                .execute();
    }
}
