package org.softuni.pathfinder.model.dto.rout;

import org.softuni.pathfinder.model.dto.picture.PictureDTO;

import java.util.List;

public class MostCommentedRouteDTO {
    private Long id;

    private String name;

    private String imageUrl;

    private String description;

    private List<PictureDTO> pictures;


    public MostCommentedRouteDTO() {
    }

    public Long getId() {
        return id;
    }

    public MostCommentedRouteDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MostCommentedRouteDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MostCommentedRouteDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MostCommentedRouteDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<PictureDTO> getPictures() {
        return pictures;
    }

    public MostCommentedRouteDTO setPictures(List<PictureDTO> pictures) {
        this.pictures = pictures;
        return this;
    }
}
