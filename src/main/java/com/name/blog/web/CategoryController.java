package com.name.blog.web;

import java.util.List;

import com.name.blog.core.security.Auth;
import com.name.blog.core.security.Role;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.name.blog.provider.dto.CategoryDTO;
import com.name.blog.provider.service.CategoryService;
import com.name.blog.web.dto.CategoryRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CategoryController {
	private final CategoryService categoryService;

	@GetMapping("/api/v1/user/{username}/categories")
	public List<CategoryDTO> getCategoriesByUsername(@PathVariable("username") String username) {
		return categoryService.selectCategoryListByUsername(username);
	}

	@PostMapping("/api/v1/category")
	@Auth(roles = {Role.USER})
	public CategoryDTO createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
		return categoryService.insertCategory(categoryRequestDTO);
	}
	
	@PutMapping("/api/v1/category/{id}")
	@Auth(roles = {Role.USER})
	public CategoryDTO updateCategory(@PathVariable("id") Long id, @RequestBody CategoryRequestDTO categoryRequestDTO) {
		return categoryService.updateCategory(id, categoryRequestDTO);
	}
	
	@DeleteMapping("/api/v1/category/{id}")
	@Auth(roles = {Role.USER})
	public CategoryDTO removeCategory(@PathVariable("id") Long id){
		return categoryService.deleteCategory(id);
	}
	
	@GetMapping("/api/v1/user/{username}/category/{id}/check-category")
	public boolean checkCategory(@PathVariable("username") String username, @PathVariable("id") Long id) {
		return categoryService.isValidCategory(username, id);
	}
}
