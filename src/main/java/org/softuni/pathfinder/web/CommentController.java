package org.softuni.pathfinder.web;

import org.softuni.pathfinder.model.dto.comments.CreateCommentDTO;
import org.softuni.pathfinder.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ModelAndView createComment(CreateCommentDTO createCommentDTO) {
        this.commentService.createComment(createCommentDTO);

        return new ModelAndView("redirect:/routes/details/" + createCommentDTO.getRouteId());
    }
}
