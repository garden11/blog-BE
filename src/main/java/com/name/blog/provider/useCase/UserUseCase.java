package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.UserDTO;
import com.name.blog.web.dto.UserRequestDTO;

import jakarta.transaction.Transactional;
import java.util.Optional;

public interface UserUseCase {
    @Transactional
    Optional<UserDTO> selectUserByUsername(String username);

    @Transactional
    UserDTO updateUserByUserName(String username, UserRequestDTO userRequestDTO);

    @Transactional
    boolean isUniqueUsername(String username);

    @Transactional
    boolean isUniqueEmail(String email);
}
