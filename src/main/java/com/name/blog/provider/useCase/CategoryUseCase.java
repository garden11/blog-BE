package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.CategoryDTO;
import com.name.blog.web.dto.CategoryRequestDTO;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CategoryUseCase {
    @Transactional
    List<CategoryDTO> selectCategoryListByUsername(String username);

    @Transactional
    CategoryDTO insertCategory(CategoryRequestDTO categoryRequestDTO);

    @Transactional
    CategoryDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO);

    @Transactional
    void deleteCategory(Long id);

    @Transactional
    boolean isValidCategory(String username, Long id);
}
