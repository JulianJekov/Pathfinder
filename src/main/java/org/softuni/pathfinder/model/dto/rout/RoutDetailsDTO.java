package org.softuni.pathfinder.model.dto.rout;

import org.softuni.pathfinder.model.enums.Level;

public class RoutDetailsDTO {

    private String authorFullName;

    private Level level;

    private String description;

    private String videoUrl;

    public String getAuthorFullName() {
        return authorFullName;
    }

    public RoutDetailsDTO setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public RoutDetailsDTO setLevel(Level level) {
        this.level = level;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RoutDetailsDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RoutDetailsDTO setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }
}
