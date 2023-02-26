package com.name.blog.provider.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.name.blog.exception.UserUpdateFailedException;
import com.name.blog.provider.useCase.UserUseCase;
import com.name.blog.web.dto.UserRequestDTO;
import org.springframework.stereotype.Service;

import com.name.blog.core.entity.User;
import com.name.blog.core.repository.UserRepository;
import com.name.blog.provider.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {
	private final UserRepository userRepository;

	@Override
	@Transactional
	public Optional<UserDTO> selectUserByUsername (String username) {
		Optional<User> optionalUser = userRepository.findByUsername(username);

		if(optionalUser.isPresent()) {
			return Optional.ofNullable(UserDTO.of(optionalUser.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public UserDTO updateUserByUserName(String username, UserRequestDTO userRequestDTO) {
		try {
			Optional<User> optionalUser = userRepository.findByUsername(username);

			if(!(optionalUser.isPresent())) {
				throw new Exception("USER_DOES_NOT_EXIST.");
			}

			User user = optionalUser.get();
			user.updateUser(userRequestDTO);

			return UserDTO.of(userRepository.save(user));
		} catch (Exception error) {
			error.printStackTrace();

			throw new UserUpdateFailedException();
		}
	}

	@Override
	@Transactional
	public boolean isUniqueUsername (String username) {
		return !(userRepository.existsByUsername(username));
	}

	@Override
	@Transactional
	public boolean isUniqueEmail(String email) {
		return !(userRepository.existsByEmail(email));
	}
}
