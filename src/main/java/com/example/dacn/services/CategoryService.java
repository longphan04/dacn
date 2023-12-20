package com.example.dacn.services;

import com.example.dacn.dtos.CategoryDTO;
import com.example.dacn.entities.Category;
import com.example.dacn.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getDefaultCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().filter(category -> category.getId() <= 8).collect(Collectors.toList());
    }

    public List<CategoryDTO> convertToCategoryDTO(List<Category> categories) {
        return categories.stream().map(category -> CategoryDTO.builder()
                .categoryId(category.getId())
                .categoryName(category.getName())
                .categoryType(category.getType())
                .build()).collect(Collectors.toList());
    }

}
