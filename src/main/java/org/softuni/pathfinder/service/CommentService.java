package org.softuni.pathfinder.service;

import org.softuni.pathfinder.model.dto.comments.CreateCommentDTO;

public interface CommentService {

    void createComment(CreateCommentDTO createCommentDTO);
}
