package org.softuni.pathfinder.service;

import org.softuni.pathfinder.model.dto.comments.CreateCommentDTO;

public interface CommentService {

    void createComment(CreateCommentDTO createCommentDTO);

    CommentViewDTO createRestComment(CreateCommentDTO createCommentDTO);

    void approve(Long id);

    void delete(Long id);
}
