package com.name.blog.provider.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.transaction.Transactional;

import com.name.blog.provider.useCase.PostViewUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.name.blog.util.Paginator;
import com.name.blog.core.entity.PostView;
import com.name.blog.core.repository.PostViewRepository;
import com.name.blog.provider.dto.PostViewDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostViewService implements PostViewUseCase {
	private static final Integer POSTS_PER_PAGE = 5;

	private final PostViewRepository postViewRepository;

	@Override
	@Transactional
	public Optional<PostViewDTO> selectPostViewById (Long id) {
		Optional<PostView> optionalPostView = postViewRepository.findById(id);

		if(optionalPostView.isPresent()) {
			return Optional.ofNullable(PostViewDTO.of(optionalPostView.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public Page<PostViewDTO> selectPostViewListByUsername (String username, Integer page) {
		List<PostViewDTO> postViewDTOList = new ArrayList<>();
		Pageable pageable = PageRequest.of(page, POSTS_PER_PAGE);

		Page<PostView> postViewList = postViewRepository.findByUsernameOrderByIdDesc(username, pageable);

		for(PostView postView : postViewList) {
			postViewDTOList.add(PostViewDTO.of(postView));
		}

		return new PageImpl<>(postViewDTOList, pageable, postViewList.getTotalElements());
	}

	@Override
	@Transactional
	public Page<PostViewDTO> selectPostViewListByCategoryId (Long categoryId, Integer page) {
		List<PostViewDTO> postViewDTOList = new ArrayList<>();
		Pageable pageable = PageRequest.of(page, POSTS_PER_PAGE);

		Page<PostView> postViewList = postViewRepository.findByCategoryIdOrderByIdDesc(categoryId, pageable);

		for(PostView postView : postViewList) {
			postViewDTOList.add(PostViewDTO.of(postView));
		}

		return new PageImpl<>(postViewDTOList, pageable, postViewList.getTotalElements());
	}
}
