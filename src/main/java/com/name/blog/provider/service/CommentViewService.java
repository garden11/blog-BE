package com.name.blog.provider.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.transaction.Transactional;

import com.name.blog.provider.useCase.CommentViewUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.name.blog.util.Paginator;
import com.name.blog.core.entity.CommentView;
import com.name.blog.core.repository.CommentViewRepository;
import com.name.blog.provider.dto.CommentViewDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentViewService implements CommentViewUseCase {
	private final Integer COMMENTS_PER_PAGE = 5;

	private final CommentViewRepository commentViewRepository;

	@Override
	@Transactional
	public Page<CommentViewDTO> selectCommentViewListByPostId(Long postId, Integer page)  {
		List<CommentViewDTO> commentViewDTOList = new ArrayList<>();
		Pageable pageable = PageRequest.of(page, COMMENTS_PER_PAGE);

    	Page<CommentView> commentViewList = commentViewRepository.findByPostId(postId, pageable);

		for(CommentView commentView : commentViewList) {
			commentViewDTOList.add(CommentViewDTO.of(commentView));
		}
		
		return new PageImpl<>(commentViewDTOList, pageable, commentViewList.getTotalElements());
	}
}
