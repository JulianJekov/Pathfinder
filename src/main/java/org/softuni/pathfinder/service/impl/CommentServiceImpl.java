package org.softuni.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.pathfinder.exceptions.RouteNotFoundException;
import org.softuni.pathfinder.exceptions.UserNotFoundException;
import org.softuni.pathfinder.model.dto.comments.CreateCommentDTO;
import org.softuni.pathfinder.model.entity.Comment;
import org.softuni.pathfinder.model.entity.Route;
import org.softuni.pathfinder.model.entity.User;
import org.softuni.pathfinder.repository.CommentRepository;
import org.softuni.pathfinder.repository.RouteRepository;
import org.softuni.pathfinder.repository.UserRepository;
import org.softuni.pathfinder.service.CommentService;
import org.softuni.pathfinder.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, RouteRepository routeRepository, ModelMapper modelMapper, LoggedUser loggedUser, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    @Override
    public void createComment(CreateCommentDTO createCommentDTO) {
        //TODO: fix saving comments with new id not replace other comment id
        Optional<Route> optionalRoute =
                this.routeRepository.findById(createCommentDTO.getRouteId());

        if (optionalRoute.isEmpty()) {
            throw new RouteNotFoundException("Route not found!");
        }

        Route route = optionalRoute.get();

        Optional<User> optionalUser =
                this.userRepository.findByUsername(this.loggedUser.getUsername());

        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }

        User user = optionalUser.get();

        Comment comment = modelMapper.map(createCommentDTO, Comment.class);
        comment.setRoute(route);
        comment.setAuthor(user);
        comment.setCreated(LocalDateTime.now());
        comment.setApproved(true);


        this.commentRepository.save(comment);
    }
}
