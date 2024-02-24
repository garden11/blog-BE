package com.name.blog.provider.service;

import java.time.temporal.ChronoUnit;
import java.util.*;

import jakarta.transaction.Transactional;

import com.name.blog.core.repository.PostRepository;
import com.name.blog.provider.useCase.CategoryUseCase;
import com.name.blog.util.DateUtil;
import org.springframework.stereotype.Service;

import com.name.blog.core.entity.Category;
import com.name.blog.core.repository.CategoryRepository;
import com.name.blog.provider.dto.CategoryDTO;
import com.name.blog.web.dto.CategoryRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryUseCase {
	private final CategoryRepository categoryRepository;

	@Override
	@Transactional
	public List<CategoryDTO> selectCategoryListByUsername(String username){
		List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();
		
    	List<Category> categoryList = categoryRepository.findByUsername(username);

		for(Category category : categoryList) {
			categoryDTOList.add(CategoryDTO.of(category));
		}
		
		return categoryDTOList;
	}

	@Override
	@Transactional
	public CategoryDTO insertCategory(CategoryRequestDTO categoryRequestDTO) {
		return CategoryDTO.of(categoryRepository.save(categoryRequestDTO.toEntity()));
	}

	@Override
	@Transactional
	public CategoryDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
		Category category = categoryRepository.findById(id).get();
		category.updateCategory(categoryRequestDTO.getName());
	
		return CategoryDTO.of(categoryRepository.save(category));
	}

	@Override
	@Transactional
	public void deleteCategory(Long id){
		Category category = categoryRepository.findById(id).get();
		categoryRepository.updateDeleteYById(category.getId());
	}

	@Override
	@Transactional
	public boolean isValidCategory (String username, Long id) {
		return categoryRepository.existsByUsernameAndId(username, id);
	}
}
