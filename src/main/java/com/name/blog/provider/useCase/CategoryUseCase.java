package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.CategoryDTO;
import com.name.blog.web.dto.CategoryRequestDTO;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CategoryUseCase {
    List<CategoryDTO> selectCategoryListByUsername(String username);

    CategoryDTO insertCategory(CategoryRequestDTO categoryRequestDTO);

    CategoryDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO);

    void deleteCategory(Long id);

    boolean isValidCategory(String username, Long id);
}
