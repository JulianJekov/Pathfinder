package org.softuni.pathfinder.model.dto.rout;

import jakarta.validation.constraints.*;
import org.softuni.pathfinder.model.entity.User;
import org.softuni.pathfinder.model.enums.CategoryNames;
import org.softuni.pathfinder.model.enums.Level;
import org.softuni.pathfinder.validations.annotations.FileAnnotation;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class AddRouteDTO {

    @Size(min = 3)
    @NotNull
    private String name;

    @Size(min = 5)
    @NotNull
    private String description;

    @FileAnnotation(contentTypes = "text/xml")
    private MultipartFile gpxCoordinates;

    @NotNull
    private Level level;

    @NotBlank(message = "")
    @Pattern(regexp = "^(https?://)?(www\\.youtube\\.com|youtu\\.be)/.+$", message = "Invalid youtube url!")
    private String videoUrl;

    @NotBlank
    private String imageUrl;

    private User author;

    @NotEmpty
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

    public String getImageUrl() {
        return imageUrl;
    }

    public AddRouteDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public MultipartFile getGpxCoordinates() {
        return gpxCoordinates;
    }

    public AddRouteDTO setGpxCoordinates(MultipartFile gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }
}
