package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.UserDTO;
import com.name.blog.web.dto.UserRequestDTO;

import jakarta.transaction.Transactional;
import java.util.Optional;

public interface UserUseCase {
    Optional<UserDTO> getUserByUsername(String username);

    UserDTO updateUserByUserName(String username, UserRequestDTO userRequestDTO);

    boolean isUniqueUsername(String username);

    boolean isUniqueEmail(String email);
}
