package org.softuni.pathfinder.web;

import org.softuni.pathfinder.model.dto.comments.CreateCommentDTO;
import org.softuni.pathfinder.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @PatchMapping("/route/{routeId}/approve/{id}")
    public ModelAndView approveComment(@PathVariable("routeId") Long routeId, @PathVariable("id") Long id) {
        this.commentService.approve(id);
        return new ModelAndView("redirect:/routes/details/" + routeId);
    }

    @DeleteMapping("/route/{routeId}/{id}")
    public ModelAndView delete(@PathVariable("routeId") Long routeId, @PathVariable("id") Long id) {
        this.commentService.delete(id);
        return new ModelAndView("redirect:/routes/details/" + routeId);
    }
}
