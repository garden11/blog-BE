package com.name.blog.provider.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import com.name.blog.provider.useCase.ProfileImageUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// 로컬 저장소 사용 시 주석 해제
//import com.name.blog.util.LocalFileUploader;
// AWS 사용 시 주석 해제
import com.name.blog.util.S3FileUploader;
import com.name.blog.core.entity.ProfileImage;
import com.name.blog.core.repository.ProfileImageRepository;
import com.name.blog.provider.dto.ProfileImageDTO;
import com.name.blog.web.dto.ProfileImageRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileImageService implements ProfileImageUseCase {
	// 로컬 저장소 사용 시 주석 해제
//	private final LocalFileUploader localFileUploader;
	// AWS 사용 시 주석 해제
	private final S3FileUploader s3FileUploader;
	private final ProfileImageRepository profileImageRepository;

	@Value("${local.profile.image.file.upload.path}")
	private String localProfileImageFileUploadPath;
	
	@Value("${local.profile.image.file.upload.handler.path}")
	private String localProfileImageFileUploadHandlerPath;

	@Override
	@Transactional
	public ProfileImageDTO insertProfileImage(ProfileImageRequestDTO profileImageRequestDTO) {
		List<ProfileImage> profileImageList= profileImageRepository.findByProfileId(Long.valueOf(profileImageRequestDTO.getProfileId()));
		List<Long> profileImageIdList = new ArrayList();

		for (ProfileImage profileImage : profileImageList) {
			profileImageIdList.add(profileImage.getId());
		}

		profileImageRepository.updateAllNotUseByIdIn(profileImageIdList);

		// AWS 사용 시 주석 해제
		Map<String, Object> uploadedFileInfo = s3FileUploader.uploadFile(profileImageRequestDTO.getImage());

		ProfileImageDTO profileImageDTO = ProfileImageDTO.of(profileImageRepository.save(ProfileImage.builder()
				.profileId(Long.valueOf(profileImageRequestDTO.getProfileId()))
				.uri(uploadedFileInfo.get(s3FileUploader.URI_KEY).toString())
				.originalName(uploadedFileInfo.get(s3FileUploader.ORIGINAL_FILE_NAME_KEY).toString())
				.name(uploadedFileInfo.get(s3FileUploader.FILE_NAME_KEY).toString())
				.build()
		));

		// 로컬 저장소 사용 시 주석 해제
//		Map<String, Object> uploadedFileInfo = localFileUploader.uploadFile(profileImageRequestDTO.getImage(), localProfileImageFileUploadPath, localProfileImageFileUploadHandlerPath);
//
//		ProfileImageDTO profileImageDTO = ProfileImageDTO.of(profileImageRepository.save(ProfileImage.builder()
//			.username(profileImageRequestDTO.getUsername())
//			.uri(uploadedFileInfo.get(localFileUploader.URI_KEY).toString())
//			.originalName(uploadedFileInfo.get(localFileUploader.ORIGINAL_FILE_NAME_KEY).toString())
//			.name(uploadedFileInfo.get(localFileUploader.CHANGED_FILE_NAME_KEY).toString())
//			.build()
//			));

		return profileImageDTO;
	}
}
