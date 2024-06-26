package com.name.blog.provider.service;

import com.name.blog.constants.Retentions;
import com.name.blog.core.entity.ProfileInfo;
import com.name.blog.core.repository.ProfileInfoRepository;
import com.name.blog.provider.dto.CommentDetailDTO;
import com.name.blog.util.DateUtil;
import jakarta.transaction.Transactional;

import com.name.blog.provider.useCase.CommentUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.name.blog.core.entity.Comment;
import com.name.blog.core.repository.CommentRepository;
import com.name.blog.provider.dto.CommentDTO;
import com.name.blog.web.dto.CommentRequestDTO;

import lombok.RequiredArgsConstructor;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentUseCase {

	private final ProfileInfoRepository profileInfoRepository;
	private final CommentRepository commentRepository;
	private final Integer COMMENTS_PER_PAGE = 5;

	private final DateUtil dateUtil= new DateUtil();

	@Override
	@Transactional
	public Page<CommentDetailDTO> getCommentDetailListByPostId(Long postId, Integer page) {
		List<CommentDetailDTO> commentDetailDTOList = new ArrayList<>();
		Pageable pageable = PageRequest.of(page, COMMENTS_PER_PAGE);

		Page<Comment> commentList = commentRepository.findByPostId(postId, pageable);
		List<ProfileInfo> profileInfoList = profileInfoRepository.findByUsernameIn(
				commentList.getContent().stream()
						.map(comment -> comment.getUsername())
						.collect(Collectors.toList())
		);



		for(Comment comment : commentList) {
			Optional<ProfileInfo> optionalProfileDetail = profileInfoList.stream()
							.filter(profileDetail -> profileDetail.getUsername().equals(comment.getUsername()))
							.findFirst();

			ProfileInfo profileInfo = optionalProfileDetail
					.orElseGet(ProfileInfo::new);

			commentDetailDTOList.add(
					CommentDetailDTO.of(
							comment,
							profileInfo
					)
			);
		}

		return new PageImpl<>(commentDetailDTOList, pageable, commentList.getTotalElements());
	}

	@Override
	@Transactional
	public CommentDTO createComment(CommentRequestDTO commentRequestDTO) {
		return CommentDTO.of(commentRepository.save(commentRequestDTO.toEntity()));
	}

	@Override
	@Transactional
	public void deleteCommentById(Long id) {
		Long expiresAt = dateUtil.createEpochSecondPlus(Retentions.COMMENT_DAYS.getValue(), ChronoUnit.DAYS);

		Comment comment = commentRepository.findById(id).orElseThrow();
		commentRepository
				.updateDeletingById(comment.getId(), expiresAt);
	}
}
