package com.name.blog.web;

import com.name.blog.core.security.Auth;
import com.name.blog.core.security.Role;
import com.name.blog.web.dto.PostRequestDTO;
import com.name.blog.web.dto.UserRequestDTO;
import org.springframework.web.bind.annotation.*;

import com.name.blog.provider.dto.UserDTO;
import com.name.blog.provider.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {
	private final UserService userService;
	
	@GetMapping("/api/v1/user/{username}")
	@Auth(roles = {Role.USER})
	public Optional <UserDTO> getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
	}

	@PutMapping("/api/v1/user/{username}")
	@Auth(roles = {Role.USER})
	public UserDTO updateUser(@PathVariable("username") String username, @RequestBody UserRequestDTO userRequestDTO) {
		return userService.updateUserByUserName(username, userRequestDTO);
	}

	@GetMapping("/api/v1/user/{username}/check-username")
	public boolean checkUsername(@PathVariable("username") String username) {
		return userService.isUniqueUsername(username);
	}

	@GetMapping("/api/v1/user/{email}/check-email")
	public boolean checkEmail(@PathVariable("email") String email) {
		return userService.isUniqueEmail(email);
	}
}
