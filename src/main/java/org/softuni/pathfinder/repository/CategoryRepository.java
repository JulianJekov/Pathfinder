package org.softuni.pathfinder.repository;

import org.softuni.pathfinder.model.entity.Category;
import org.softuni.pathfinder.model.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Set<Category> findByNameIn(Set<CategoryType> categories);
}
