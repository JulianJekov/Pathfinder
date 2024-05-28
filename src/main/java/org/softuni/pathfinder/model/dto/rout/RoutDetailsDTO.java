package org.softuni.pathfinder.model.dto.rout;

import org.softuni.pathfinder.model.enums.Level;

public class RoutDetailsDTO {

    private String name;

    private String authorUsername;

    private Level level;

    private String description;

    private String videoUrl;

    //Todo: gpx coordinates
    //Todo: video url
    //Todo: choose picture
    //Todo: total distance


    public String getName() {
        return name;
    }

    public RoutDetailsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public RoutDetailsDTO setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
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
