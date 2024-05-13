package org.softuni.pathfinder.model.dto;

import org.softuni.pathfinder.model.enums.CategoryType;
import org.softuni.pathfinder.model.enums.Level;

import java.util.Set;

public class AddRouteDTO {

    private String name;

    private String description;

    private Level level;

    private String videoUrl;

    private Set<CategoryType> categories;

    public AddRouteDTO() {
    }

    public String getName() {
        return name;
    }

    public AddRouteDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddRouteDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public AddRouteDTO setLevel(Level level) {
        this.level = level;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public AddRouteDTO setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public Set<CategoryType> getCategories() {
        return categories;
    }

    public AddRouteDTO setCategories(Set<CategoryType> categories) {
        this.categories = categories;
        return this;
    }
}
