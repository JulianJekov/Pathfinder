package org.softuni.pathfinder.web.rest;

import org.softuni.pathfinder.model.dto.comments.CommentViewDTO;
import org.softuni.pathfinder.model.dto.comments.CreateCommentDTO;
import org.softuni.pathfinder.service.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {

    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public CommentViewDTO create(@RequestBody CreateCommentDTO createCommentDTO) {
        return commentService.createRestComment(createCommentDTO);
    }
}
