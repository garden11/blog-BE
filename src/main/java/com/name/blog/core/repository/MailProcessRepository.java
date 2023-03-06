package com.name.blog.core.repository;

import com.name.blog.core.entity.MailProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailProcessRepository extends JpaRepository<MailProcess, Long> {
    Optional<MailProcess> findByProcessToken(String processToken);

    @Modifying
    @Query(value="UPDATE mail_process SET delete_yn = 'Y' WHERE id = :id", nativeQuery=true)
    int updateProcessYById(@Param("id") Long id);

    @Modifying
    @Query(value="UPDATE mail_process SET process_yn = 'Y' WHERE email = :email", nativeQuery=true)
    int updateAllProcessYByEmail(@Param("email") String email);
}
