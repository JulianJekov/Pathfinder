package org.softuni.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.pathfinder.helpers.LoggedUserHelperService;
import org.softuni.pathfinder.model.dto.user.UserProfileDTO;
import org.softuni.pathfinder.model.entity.User;
import org.softuni.pathfinder.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final LoggedUserHelperService loggedUserHelperService;
    private final ModelMapper modelMapper;

    public UserServiceImpl(LoggedUserHelperService loggedUserHelperService, ModelMapper modelMapper) {
        this.loggedUserHelperService = loggedUserHelperService;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserProfileDTO getUserProfile() {
        User user = this.loggedUserHelperService.getCurrentUser();
        return this.modelMapper.map(user, UserProfileDTO.class);
    }
}
