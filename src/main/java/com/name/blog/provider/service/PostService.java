package com.name.blog.provider.service;

import jakarta.transaction.Transactional;

import com.name.blog.core.repository.PostImageRepository;
import com.name.blog.provider.useCase.PostUseCase;
import com.name.blog.util.DateUtil;
import org.springframework.stereotype.Service;

import com.name.blog.core.entity.Post;
import com.name.blog.core.repository.PostRepository;
import com.name.blog.provider.dto.PostDTO;
import com.name.blog.web.dto.PostRequestDTO;

import lombok.RequiredArgsConstructor;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService implements PostUseCase {
	private final PostRepository postRepository;
	private final PostImageRepository postImageRepository;

	@Override
	@Transactional
	public Optional<PostDTO> selectPostByPostId (Long id) {
		Optional<Post> optionalPost = postRepository.findById(id);

		if(optionalPost.isPresent()) {
			return Optional.ofNullable(PostDTO.of(optionalPost.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public PostDTO insertPost(PostRequestDTO postRequestDTO) {
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
