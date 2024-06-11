package org.softuni.pathfinder.model.dto.comments;

public class CommentViewDTO {

    private Long id;

    private String content;

    private String authorUsername;

    private Boolean approved;

    public CommentViewDTO() {
    }

    public String getContent() {
        return content;
    }

    public CommentViewDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public CommentViewDTO setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
