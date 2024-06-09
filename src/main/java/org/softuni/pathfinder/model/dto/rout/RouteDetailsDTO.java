package org.softuni.pathfinder.model.dto.rout;

import org.softuni.pathfinder.model.dto.comments.CommentViewDTO;
import org.softuni.pathfinder.model.dto.picture.PictureDTO;
import org.softuni.pathfinder.model.enums.Level;

import java.util.List;

public class RouteDetailsDTO {

    private Long id;

    private String name;

    private String authorUsername;

    private Level level;

    private String description;

    private String videoUrl;

    private List<CommentViewDTO> comments;

    private List<PictureDTO> pictures;

    //Todo: gpx coordinates
    //Todo: video url
    //Todo: choose picture
    //Todo: total distance


    public String getName() {
        return name;
    }

    public RouteDetailsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public RouteDetailsDTO setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public RouteDetailsDTO setLevel(Level level) {
        this.level = level;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteDetailsDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteDetailsDTO setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public Long getId() {
        return id;
    }

    public RouteDetailsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public List<CommentViewDTO> getComments() {
        return comments;
    }

    public RouteDetailsDTO setComments(List<CommentViewDTO> comments) {
        this.comments = comments;
        return this;
    }

    public List<PictureDTO> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureDTO> pictures) {
        this.pictures = pictures;
    }
}
