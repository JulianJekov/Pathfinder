package org.softuni.pathfinder.service;

import org.softuni.pathfinder.model.entity.Category;
import org.softuni.pathfinder.model.enums.CategoryNames;

import java.util.Set;


public interface CategoryService {
    Set<Category> findByNameIn (Set<CategoryNames> categoryNames);
}
