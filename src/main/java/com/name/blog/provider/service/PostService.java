package com.name.blog.provider.service;

import com.name.blog.constants.Retentions;
import com.name.blog.core.entity.PostInfo;
import com.name.blog.core.entity.ProfileInfo;
import com.name.blog.core.repository.PostInfoRepository;
import com.name.blog.core.repository.ProfileInfoRepository;
import com.name.blog.provider.dto.PostDetailDTO;
import com.name.blog.provider.eventListener.event.PostDeletedEvent;
import com.name.blog.util.DateUtil;
import jakarta.transaction.Transactional;

import com.name.blog.core.repository.PostImageRepository;
import com.name.blog.provider.useCase.PostUseCase;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.name.blog.core.entity.Post;
import com.name.blog.core.repository.PostRepository;
import com.name.blog.provider.dto.PostDTO;
import com.name.blog.web.dto.PostRequestDTO;

import lombok.RequiredArgsConstructor;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService implements PostUseCase {

	private final ApplicationEventPublisher eventPublisher;

	private final PostRepository postRepository;
	private final PostInfoRepository postInfoRepository;
	private final PostImageRepository postImageRepository;
	private final ProfileInfoRepository profileInfoRepository;

	private static final Integer POSTS_PER_PAGE = 5;

	private final DateUtil dateUtil = new DateUtil();

	@Override
	@Transactional
	public Page<PostDetailDTO> getPostDetailList(Integer page) {
		List<PostDetailDTO> postDetailDTOList = new ArrayList<>();

		Pageable pageable = PageRequest.of(page, POSTS_PER_PAGE);
		Page<PostInfo> postInfoList = postInfoRepository.findOrderByIdDesc(pageable);

		List<ProfileInfo> profileInfoList = profileInfoRepository.findByUsernameIn(
				postInfoList.getContent().stream()
						.map(postInfo -> postInfo.getUsername())
						.collect(Collectors.toList())
		);

		for(PostInfo postInfo : postInfoList) {
			Optional<ProfileInfo> optionalProfileInfo = profileInfoList.stream()
					.filter(profileInfo -> profileInfo.getUsername() == postInfo.getUsername())
					.findFirst();

			ProfileInfo profileInfo = optionalProfileInfo
					.orElseGet(ProfileInfo::new);

			postDetailDTOList.add(PostDetailDTO.of(postInfo, profileInfo));
		}

		return new PageImpl<>(postDetailDTOList, pageable, postInfoList.getTotalElements());
	}


	@Override
	@Transactional
	public Page<PostDetailDTO> getPostDetailListByUsername(String username, Integer page) {
		List<PostDetailDTO> postDetailDTOList = new ArrayList<>();

		Pageable pageable = PageRequest.of(page, POSTS_PER_PAGE);
		Page<PostInfo> postInfoList = postInfoRepository.findByUsernameOrderByIdDesc(username, pageable);

		List<ProfileInfo> profileInfoList = profileInfoRepository.findByUsernameIn(
				postInfoList.getContent().stream()
						.map(postInfo -> postInfo.getUsername())
						.collect(Collectors.toList())
		);

		for(PostInfo postInfo : postInfoList) {
			Optional<ProfileInfo> optionalProfileInfo = profileInfoList.stream()
					.filter(profileInfo -> profileInfo.getUsername() == postInfo.getUsername())
					.findFirst();

			ProfileInfo profileInfo = optionalProfileInfo
					.orElseGet(ProfileInfo::new);

			postDetailDTOList.add(PostDetailDTO.of(postInfo, profileInfo));
		}

		return new PageImpl<>(postDetailDTOList, pageable, postInfoList.getTotalElements());
	}

	@Override
	public Page<PostDetailDTO> getPostDetailListByTagId(Long PostTagId, Integer page) {
		List<PostDetailDTO> postDetailDTOList = new ArrayList<>();

		Pageable pageable = PageRequest.of(page, POSTS_PER_PAGE);
		Page<PostInfo> postInfoList = postInfoRepository.findByTagIdOrderByIdDesc(PostTagId, pageable);

		List<ProfileInfo> profileInfoList = profileInfoRepository.findByUsernameIn(
				postInfoList.getContent().stream()
						.map(postInfo -> postInfo.getUsername())
						.collect(Collectors.toList())
		);

		for(PostInfo postInfo : postInfoList) {
			Optional<ProfileInfo> optionalProfileInfo = profileInfoList.stream()
					.filter(profileInfo -> profileInfo.getUsername() == postInfo.getUsername())
					.findFirst();

			ProfileInfo profileInfo = optionalProfileInfo
					.orElseGet(ProfileInfo::new);

			postDetailDTOList.add(PostDetailDTO.of(postInfo, profileInfo));
		}

		return new PageImpl<>(postDetailDTOList, pageable, postInfoList.getTotalElements());
	}

	@Override
	@Transactional
	public Optional<PostDetailDTO> getPostDetailById(Long id) {
		Optional<PostInfo> optionalPostInfo = postInfoRepository.findById(id);


		if(optionalPostInfo.isPresent()) {
			PostInfo postInfo = optionalPostInfo.get();

			Optional<ProfileInfo> optionalProfileInfo = profileInfoRepository.findByUsername(postInfo.getUsername());
			ProfileInfo profileInfo = optionalProfileInfo
					.orElseGet(ProfileInfo::new);

			return Optional.ofNullable(PostDetailDTO.of(postInfo, profileInfo));
		} else {
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public Optional<PostDTO> getPostByPostId (Long id) {
		Optional<Post> optionalPost = postRepository.findById(id);

		if(optionalPost.isPresent()) {
			return Optional.ofNullable(PostDTO.of(optionalPost.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public PostDTO createPost(PostRequestDTO postRequestDTO) {
		Long expiresAt = dateUtil.createEpochSecondPlus(Retentions.POST_DAYS.getValue(), ChronoUnit.DAYS);

		Post post = postRequestDTO.toEntity();
		post.setExpiresAt(expiresAt);

		return PostDTO.of(postRepository.save(post));
	}

	@Override
	@Transactional
	public PostDTO updatePostById(Long id, PostRequestDTO postRequestDTO) {
		Post post = postRepository.findById(id).orElseThrow();

		Long expiresAt = dateUtil.createEpochSecondPlus(Retentions.POST_IMAGE_DAYS.getValue(), ChronoUnit.DAYS);

		postImageRepository.updateNotUsingByPostId(id, expiresAt);
		postImageRepository.updateUsingByPostIdAndUriIn(id, postRequestDTO.getImageUriList());
		
		post.updatePost(postRequestDTO);

		if(post.getRegisterYN().equals("N")) {
			post.registerPost();
			post.unsetExpiresAt();
		}

		return PostDTO.of(postRepository.save(post));
	}

	@Override
	@Transactional
	public void deletePostById(Long id) {

		Long expiresAt = dateUtil.createEpochSecondPlus(Retentions.POST_DAYS.getValue(), ChronoUnit.DAYS);

		Post post = postRepository.findById(id).orElseThrow();

		postRepository.updateDeletingById(post.getId(), expiresAt);
		eventPublisher.publishEvent(new PostDeletedEvent(this, post.getId()));
	}

	@Override
	@Transactional
	public boolean isValidPost (String username, Long id) {

		return postRepository.existsByUsernameAndId(username, id);
	}
}
