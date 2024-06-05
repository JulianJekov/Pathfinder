package org.softuni.pathfinder.model.dto.rout;

import org.softuni.pathfinder.validations.annotations.FileAnnotation;
import org.springframework.web.multipart.MultipartFile;

public class UploadPictureRouteDTO {
    private Long id;

    @FileAnnotation(contentTypes = {"image/png", "image/jpeg"})
    private MultipartFile picture;

    public UploadPictureRouteDTO() {
    }

    public Long getId() {
        return id;
    }

    public UploadPictureRouteDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public UploadPictureRouteDTO setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
