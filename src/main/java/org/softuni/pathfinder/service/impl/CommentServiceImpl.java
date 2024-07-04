package org.softuni.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.pathfinder.exceptions.CommentNotFoundException;
import org.softuni.pathfinder.exceptions.RouteNotFoundException;
import org.softuni.pathfinder.helpers.LoggedUserHelperService;
import org.softuni.pathfinder.exceptions.UserNotFoundException;
import org.softuni.pathfinder.helpers.LoggedUserHelperService;
import org.softuni.pathfinder.model.dto.comments.CommentViewDTO;

import org.softuni.pathfinder.model.dto.comments.CreateCommentDTO;
import org.softuni.pathfinder.model.entity.Comment;
import org.softuni.pathfinder.model.entity.Route;
import org.softuni.pathfinder.model.entity.User;
import org.softuni.pathfinder.repository.CommentRepository;
import org.softuni.pathfinder.repository.RouteRepository;
import org.softuni.pathfinder.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final LoggedUserHelperService loggedUserHelperService;

    public CommentServiceImpl(CommentRepository commentRepository,
                              RouteRepository routeRepository,
                              ModelMapper modelMapper,
                              LoggedUserHelperService loggedUserHelperService) {
        this.commentRepository = commentRepository;
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.loggedUserHelperService = loggedUserHelperService;
    }

    @Override
    public void createComment(CreateCommentDTO createCommentDTO) {
        Route route = this.routeRepository.findById(createCommentDTO.getRouteId()).orElseThrow(
                () -> new RouteNotFoundException("Route not found!"));

        User user = loggedUserHelperService.getCurrentUser();

        Comment comment = modelMapper.map(createCommentDTO, Comment.class);
        comment.setRoute(route);
        comment.setAuthor(user);

        this.commentRepository.save(comment);
    }

    @Override
    public CommentViewDTO createRestComment(CreateCommentDTO createCommentDTO) {
        Route route = this.routeRepository.findById(createCommentDTO.getRouteId()).orElseThrow(
                () -> new RouteNotFoundException("Route not found!"));

        User user = loggedUserHelperService.getCurrentUser();

        Comment comment = modelMapper.map(createCommentDTO, Comment.class);
        comment.setRoute(route);
        comment.setAuthor(user);

       return modelMapper.map(this.commentRepository.save(comment) ,CommentViewDTO.class);
    }

    @Override
    public void approve(Long id) {
        Comment comment = this.commentRepository.findById(id).orElseThrow(
                () -> new CommentNotFoundException("Comment not found!"));

        comment.setApproved(true);
        this.commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        this.commentRepository.deleteById(id);
    }

    @Override
    public Long getMostCommentedRouteId() {
        return this.commentRepository.getMostCommentedRoutId();
    }
}
