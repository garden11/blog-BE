package com.name.blog.core.repository;

import com.name.blog.core.entity.MailProcess;
import com.name.blog.core.repository.custom.MailProcessRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailProcessRepository extends JpaRepository<MailProcess, Long> , MailProcessRepositoryCustom {
    Optional<MailProcess> findByProcessToken(String processToken);
}
