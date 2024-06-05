package org.softuni.pathfinder.model.dto.comments;

public class CreateCommentDTO {

    private Long routeId;

    private String textContent;

    public CreateCommentDTO() {
    }

    public Long getRouteId() {
        return routeId;
    }

    public CreateCommentDTO setRouteId(Long routeId) {
        this.routeId = routeId;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public CreateCommentDTO setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }
}
