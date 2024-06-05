package org.softuni.pathfinder.model.dto.comments;

public class CommentViewDTO {
    private String content;

    private String authorUsername;

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
}
