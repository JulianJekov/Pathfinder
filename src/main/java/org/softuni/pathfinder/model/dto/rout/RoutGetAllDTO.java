package org.softuni.pathfinder.model.dto.rout;

public class RoutGetAllDTO {

    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    public Long getId() {
        return id;
    }

    public RoutGetAllDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoutGetAllDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RoutGetAllDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public RoutGetAllDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
