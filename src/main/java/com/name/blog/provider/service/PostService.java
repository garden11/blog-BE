package com.name.blog.provider.service;

import com.name.blog.core.entity.PostInfo;
import com.name.blog.core.repository.PostInfoRepository;
import com.name.blog.provider.dto.PostDetailDTO;
import jakarta.transaction.Transactional;

import com.name.blog.core.repository.PostImageRepository;
import com.name.blog.provider.useCase.PostUseCase;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService implements PostUseCase {
	private static final Integer POSTS_PER_PAGE = 5;

	private final PostRepository postRepository;
	private final PostInfoRepository postInfoRepository;
	private final PostImageRepository postImageRepository;

	@Override
	@Transactional
	public Page<PostDetailDTO> getPostDetailList(Integer page) {
		List<PostDetailDTO> postDetailDTOList = new ArrayList<>();

		Pageable pageable = PageRequest.of(page, POSTS_PER_PAGE);
		Page<PostInfo> postInfoList = postInfoRepository.findOrderByIdDesc(pageable);

		for(PostInfo postInfo : postInfoList) {
			postDetailDTOList.add(PostDetailDTO.of(postInfo));
		}

		return new PageImpl<>(postDetailDTOList, pageable, postInfoList.getTotalElements());
	}


	@Override
	@Transactional
	public Page<PostDetailDTO> getPostDetailListByUsername(String username, Integer page) {
		List<PostDetailDTO> postDetailDTOList = new ArrayList<>();

		Pageable pageable = PageRequest.of(page, POSTS_PER_PAGE);
		Page<PostInfo> postInfoList = postInfoRepository.findByUsernameOrderByIdDesc(username, pageable);

		for(PostInfo postInfo : postInfoList) {
			postDetailDTOList.add(PostDetailDTO.of(postInfo));
		}

		return new PageImpl<>(postDetailDTOList, pageable, postInfoList.getTotalElements());
	}

	@Override
	@Transactional
	public Optional<PostDetailDTO> getPostDetailById(Long id) {
		Optional<PostInfo> optionalPostDetail = postInfoRepository.findById(id);

		if(optionalPostDetail.isPresent()) {
			return Optional.ofNullable(PostDetailDTO.of(optionalPostDetail.get()));
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
		Post post = postRequestDTO.toEntity();

		return PostDTO.of(postRepository.save(post));
	}

	@Override
	@Transactional
	public PostDTO updatePostById(Long id, PostRequestDTO postRequestDTO) {
		Post post = postRepository.findById(id).orElseThrow();

		postImageRepository.updateAllUseNByPostId(id);
		postImageRepository.updateAllUseYByPostIdAndUriIn(id, postRequestDTO.getImageUriList());
		
		post.updatePost(postRequestDTO);

		if(post.getRegisterYN().equals("N")) {
			post.registerPost();
		}

		return PostDTO.of(postRepository.save(post));
	}

	@Override
	@Transactional
	public void deletePostById(Long id) {
		Post post = postRepository.findById(id).orElseThrow();

		postRepository.updateDeleteYById(post.getId());
	}

	@Override
	@Transactional
	public boolean isValidPost (String username, Long id) {

		return postRepository.existsByUsernameAndId(username, id);
	}
}
