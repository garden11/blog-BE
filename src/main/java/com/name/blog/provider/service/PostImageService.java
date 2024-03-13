package com.name.blog.provider.service;

import java.io.IOException;
import java.util.*;

import jakarta.transaction.Transactional;

import com.name.blog.provider.useCase.PostImageUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// 로컬 저장소 사용 시 주석 해제
import com.name.blog.util.LocalFileUploader;
// AWS 사용 시 주석 해제
//import com.name.blog.util.S3FileUploader;
import com.name.blog.core.entity.PostImage;
import com.name.blog.core.repository.PostImageRepository;
import com.name.blog.provider.dto.PostImageDTO;
import com.name.blog.web.dto.PostImageRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostImageService implements PostImageUseCase {
	// 로컬 저장소 사용 시 주석 해제
	private final LocalFileUploader localFileUploader;
	// AWS 사용 시 주석 해제
//	private final S3FileUploader s3FileUploader;

	// 로컬 저장소 사용 시 주석 해제
	@Value("${local.post.image.file.upload.path}")
	private String localPostImageFileUploadPath;

	@Value("${local.post.image.file.upload.handler.path}")
	private String localPostImageFileUploadHandlerPath;

	@Value("${domain.uri}")
	private String domainUri;

	private final PostImageRepository postImageRepository;

	@Override
	@Transactional
	public PostImageDTO createPostImage(PostImageRequestDTO postImageRequestDTO) {
		// AWS 사용 시 주석 해제
//		Map<String, Object> uploadedFileInfo = s3FileUploader.uploadFile(postImageRequestDTO.getImage());
//
//		PostImageDTO postImageDTO = PostImageDTO.of(postImageRepository.save(PostImage.builder()
//				.postId(postImageRequestDTO.getPostId())
//				.uri(uploadedFileInfo.get(s3FileUploader.URI_KEY).toString())
//				.originalName(uploadedFileInfo.get(s3FileUploader.ORIGINAL_FILE_NAME_KEY).toString())
//				.name(uploadedFileInfo.get(s3FileUploader.FILE_NAME_KEY).toString())
//				.build()
//		));

		// 로컬 저장소 사용 시 주석 해제
		Map<String, Object> uploadedFileInfo = null;
		try {
			uploadedFileInfo = localFileUploader.uploadFile(postImageRequestDTO.getImage(), localPostImageFileUploadPath, localPostImageFileUploadHandlerPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		PostImageDTO postImageDTO = PostImageDTO.of(postImageRepository.save(PostImage.builder()
				.postId(postImageRequestDTO.getPostId())
				.uri(domainUri + uploadedFileInfo.get(localFileUploader.URI_KEY).toString())
				.originalName(uploadedFileInfo.get(localFileUploader.ORIGINAL_FILE_NAME_KEY).toString())
				.name(uploadedFileInfo.get(localFileUploader.CHANGED_FILE_NAME_KEY).toString())
				.build()));

		return postImageDTO;
	}
}
