package org.softuni.pathfinder.model.dto.comments;

import java.time.LocalDateTime;

public class CreateCommentDTO {

    private Long id;

    private Long routeId;

    private String textContent;

    private LocalDateTime created = LocalDateTime.now();

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
