package com.ravilyahya.blogapp.service.impl;

import com.ravilyahya.blogapp.exception.ResourceNotFoundException;
import com.ravilyahya.blogapp.model.Category;
import com.ravilyahya.blogapp.payloads.CategoryDTO;
import com.ravilyahya.blogapp.repository.CategoryRepository;
import com.ravilyahya.blogapp.service.CategoryService;
import com.ravilyahya.blogapp.util.DTOConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final DTOConverter dtoConverter;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category savedCategory = categoryRepository.save(dtoConverter.categoryDTOToCategory(categoryDTO));
        return  dtoConverter.categoryToCategoryDTO(savedCategory);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        Category existingCategory = categoryRepository.
                findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category","category id",categoryId));

        existingCategory.setCategoryTitle(categoryDTO.getCategoryTitle());
        existingCategory.setCategoryDescription(categoryDTO.getCategoryDescription());
        Category updatedCategory = categoryRepository.save(existingCategory);
        return dtoConverter.categoryToCategoryDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category existingCategory = categoryRepository.
                findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category","category id",categoryId));
        categoryRepository.delete(existingCategory);
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {
        Category existingCategory = categoryRepository.
                findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category","category id",categoryId));
        return dtoConverter.categoryToCategoryDTO(existingCategory);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = categories.stream().map(dtoConverter::categoryToCategoryDTO).toList();
        return categoryDTOS;
    }
}
