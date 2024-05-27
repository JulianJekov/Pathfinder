package org.softuni.pathfinder.service.impl;

import org.softuni.pathfinder.model.entity.Category;
import org.softuni.pathfinder.model.enums.CategoryNames;
import org.softuni.pathfinder.repository.CategoryRepository;
import org.softuni.pathfinder.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> findByNameIn(Set<CategoryNames> categoryNames) {
        return this.categoryRepository.findByNameIn(categoryNames);
    }
}
