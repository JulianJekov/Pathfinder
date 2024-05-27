package org.softuni.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.pathfinder.exceptions.UserNotFoundException;
import org.softuni.pathfinder.model.dto.user.UserProfileDTO;
import org.softuni.pathfinder.model.entity.User;
import org.softuni.pathfinder.repository.UserRepository;
import org.softuni.pathfinder.service.UserService;
import org.softuni.pathfinder.session.LoggedUser;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, LoggedUser loggedUser, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserProfileDTO getUserProfile() {
        String username = loggedUser.getUsername();
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " was not found!"));

        return this.modelMapper.map(user, UserProfileDTO.class);
    }
}
