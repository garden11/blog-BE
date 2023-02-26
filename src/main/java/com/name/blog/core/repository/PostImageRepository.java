package com.name.blog.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.name.blog.core.entity.PostImage;

import java.util.List;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Long> {
    @Modifying
    @Query(value="UPDATE post_image SET use_yn = 'N' WHERE post_id = :postId", nativeQuery=true)
    int updateAllNotUseByPostId(@Param("postId")Long postId);

    @Modifying
    @Query(value="UPDATE post_image SET use_yn = 'Y' WHERE post_id = :postId AND uri IN (:uriList)", nativeQuery=true)
    int updateAllUseByPostIdAndUriIn(@Param("postId")Long postId, @Param("uriList") List<String> uriList);

    @Query(value="SELECT id, name FROM post_image WHERE expires_at IS NOT NULL AND expires_at < UNIX_TIMESTAMP() AND id >= :id", nativeQuery=true)
    List<Object[]> selectIdsAndNamesExpired(@Param("id") Long id);

    @Modifying
    @Query(value="DELETE FROM post_image WHERE id IN :idList", nativeQuery=true)
    int deleteByIdIn(@Param("idList") List<Long>idList);
}
