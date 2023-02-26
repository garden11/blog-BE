package com.name.blog.provider.service;

import java.util.*;

import javax.transaction.Transactional;

import com.name.blog.provider.useCase.PostImageUseCase;
import org.springframework.stereotype.Service;

// 로컬 저장소 사용 시 주석 해제
import com.name.blog.util.LocalFileUploader;
// AWS 사용 시 주석 해제
import com.name.blog.util.S3FileUploader;
import com.name.blog.core.entity.PostImage;
import com.name.blog.core.repository.PostImageRepository;
import com.name.blog.provider.dto.PostImageDTO;
import com.name.blog.web.dto.PostImageRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostImageService implements PostImageUseCase {
	// 로컬 저장소 사용 시 주석 해제
//	private final LocalFileUploader localFileUploader;
	// AWS 사용 시 주석 해제
	private final S3FileUploader s3FileUploader;

	// 로컬 저장소 사용 시 주석 해제
//	@Value("${local.post.image.file.upload.path}")
//	private String localPostImageFileUploadPath;
//
//	@Value("${local.post.image.file.upload.handler.path}")
//	private String localPostImageFileUploadHandlerPath;

	private final PostImageRepository postImageRepository;

	@Override
	@Transactional
	public PostImageDTO insertPostImage(PostImageRequestDTO postImageRequestDTO) {
		// AWS 사용 시 주석 해제
		Map<String, Object> uploadedFileInfo = s3FileUploader.uploadFile(postImageRequestDTO.getImage());

		PostImageDTO postImageDTO = PostImageDTO.of(postImageRepository.save(PostImage.builder()
				.postId(postImageRequestDTO.getPostId())
				.uri(uploadedFileInfo.get(s3FileUploader.URI_KEY).toString())
				.originalName(uploadedFileInfo.get(s3FileUploader.ORIGINAL_FILE_NAME_KEY).toString())
				.name(uploadedFileInfo.get(s3FileUploader.FILE_NAME_KEY).toString())
				.build()
		));

		// 로컬 저장소 사용 시 주석 해제
//		Map<String, Object> uploadedFileInfo = localFileUploader.uploadFile(postImageRequestDTO.getImage(), localPostImageFileUploadPath, localPostImageFileUploadHandlerPath);
//
//		PostImageDTO postImageDTO = PostImageDTO.of(postImageRepository.save(PostImage.builder()
//				.postId(postImageRequestDTO.getPostId())
//				.uri(uploadedFileInfo.get(localFileUploader.URI_KEY).toString())
//				.originalName(uploadedFileInfo.get(localFileUploader.ORIGINAL_FILE_NAME_KEY).toString())
//				.name(uploadedFileInfo.get(localFileUploader.CHANGED_FILE_NAME_KEY).toString())
//				.build();

		return postImageDTO;
	}

	@Override
	@Transactional
	public void updatePostImagesByPostIdAndUriList(Long postId, List<String> uriList) {
		postImageRepository.updateAllNotUseByPostId(postId);
		postImageRepository.updateAllUseByPostIdAndUriIn(postId, uriList);
	}
}
