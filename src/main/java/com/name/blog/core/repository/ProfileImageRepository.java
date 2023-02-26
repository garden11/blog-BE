package com.name.blog.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.name.blog.core.entity.ProfileImage;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
	List<ProfileImage> findByProfileId(Long profileId);

	@Modifying
	@Query(value="UPDATE profile_image SET use_yn = 'N' WHERE id IN (:idList)", nativeQuery=true)
	int updateAllNotUseByIdIn(@Param("idList") List<Long> idList);

	@Query(value="SELECT id, name FROM profile_image WHERE expires_at IS NOT NULL AND expires_at < UNIX_TIMESTAMP() AND id >= :id", nativeQuery=true)
	List<Object[]> selectIdsAndNamesExpired(@Param("id") Long id);

	@Modifying
	@Query(value="DELETE FROM profile_image WHERE id IN :idList", nativeQuery=true)
	int deleteByIdIn(@Param("idList") List<Long>idList);
}
