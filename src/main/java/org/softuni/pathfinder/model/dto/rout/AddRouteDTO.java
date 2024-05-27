package org.softuni.pathfinder.model.dto.rout;

import org.softuni.pathfinder.model.entity.User;
import org.softuni.pathfinder.model.enums.CategoryNames;
import org.softuni.pathfinder.model.enums.Level;

import java.util.Set;

public class AddRouteDTO {

    private String name;

    private String description;

    private Level level;

    private String videoUrl;

    private User author;

    private Set<CategoryNames> categories;

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

    public Set<CategoryNames> getCategories() {
        return categories;
    }

    public AddRouteDTO setCategories(Set<CategoryNames> categories) {
        this.categories = categories;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public AddRouteDTO setAuthor(User author) {
        this.author = author;
        return this;
    }
}
