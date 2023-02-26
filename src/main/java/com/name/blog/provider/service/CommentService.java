package com.name.blog.provider.service;

import javax.transaction.Transactional;

import com.name.blog.provider.useCase.CommentUseCase;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.name.blog.core.entity.Comment;
import com.name.blog.core.repository.CommentRepository;
import com.name.blog.provider.dto.CommentDTO;
import com.name.blog.web.dto.CommentRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentUseCase {
	private final CommentRepository commentRepository;

	@Override
	@Transactional
	public CommentDTO insertComment(@RequestBody CommentRequestDTO commentRequestDTO) {
		return CommentDTO.of(commentRepository.save(commentRequestDTO.toEntity()));
	}

	@Override
	@Transactional
	public CommentDTO deleteCommentById(Long id) {
		Comment comment = commentRepository.findById(id).orElseThrow();
		
		comment.deleteComment();
		
		return CommentDTO.of(commentRepository.save(comment));
	}
}
