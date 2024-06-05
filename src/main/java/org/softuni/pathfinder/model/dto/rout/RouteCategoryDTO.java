package org.softuni.pathfinder.model.dto.rout;

public class RouteCategoryDTO {
    private Long id;

    private String name;

    private String description;

    public Long getId() {
        return id;
    }

    public RouteCategoryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteCategoryDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteCategoryDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
